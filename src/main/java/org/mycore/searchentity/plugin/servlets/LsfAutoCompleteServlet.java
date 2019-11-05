package org.mycore.searchentity.plugin.servlets;

import org.mycore.common.MCRException;
import org.mycore.common.config.MCRConfiguration2;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;
import org.mycore.searchentity.plugin.soap.client.SOAPSearch;
import org.mycore.searchentity.plugin.soap.client.SOAPSearchServiceLocator;

import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/** Servlet implementation provides an autocomplete service for lsf */
public class LsfAutoCompleteServlet extends MCRServlet {

    private static final long serialVersionUID = 1L;

    /*
     * Encoding of XML Strings */
    private final static String encoding = "ISO-8859-15";

    private static Logger LOGGER = LogManager.getLogger(LsfAutoCompleteServlet.class);

    /*
     * get maxSize via MCRConfiguration..
     */
    private int maxsize;

    /* The client instance of LSF SOAPSearch web service */
    private SOAPSearch soapsearch;

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.info("Initializing client instance of LSF SOAPSearch web service");

        try {
            soapsearch = new SOAPSearchServiceLocator().getsoapsearch();
        } catch (ServiceException ex) {

            String msg = "Could not locate HIS LSF WebService";
            throw new MCRException(msg, ex);
        }

        maxsize = MCRConfiguration2.getInt("mir-searchentity-plugin.lsf.autocomplete.maxsize").orElse(30);

    }

    @Override
    protected void doGetPost(MCRServletJob job) throws Exception {

        HttpServletRequest request = job.getRequest();

        String searchParam = (String) request.getParameter("searchParam");
        if (searchParam == null || searchParam.equals(""))
            throw new ServletException("Invalid or non-existent search parameter in LsfAutoCompleteServlet servlet.");

        HttpServletResponse response = job.getResponse();

        PrintWriter autocompleteOut = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject soapsearchData = null;
        JSONObject autoCompleteObj = new JSONObject();

        String requestSoap = buildRequest(searchParam);

        try {

            soapsearchData = XML.toJSONObject(soapsearch.search(requestSoap));

            JSONObject resultList = soapsearchData.getJSONObject("result")
                .getJSONObject("success")
                .getJSONObject("list");

            JSONArray resultObjects = new JSONArray();

            try {
                resultObjects = resultList.getJSONArray("object");
            } catch (JSONException jsonException) {
                resultObjects = resultObjects.put(resultList.getJSONObject("object"));
            }
            int currentSize = resultObjects.length() <= maxsize ? resultObjects.length() : maxsize;

            LOGGER.info(
                "Found " + resultObjects.length() + " result object Entries with soapsearch for searchParam "
                    + searchParam + ": First "
                    + currentSize + " will be used for autocomplete");

            for (int ind = 0; ind < currentSize; ind++) {

                JSONArray currentPersonMin = resultObjects.getJSONObject(ind).getJSONArray("attribute");
                BigDecimal personIdentifier = null;
                String personName = "";

                for (int indPerson = 0; indPerson < currentPersonMin.length(); indPerson++) {

                    JSONObject currentPersonAttribute = currentPersonMin.getJSONObject(indPerson);

                    if (currentPersonAttribute.getString("name").equals("ID")) {

                        try {
                            personIdentifier = currentPersonAttribute.getBigDecimal("value");
                        } catch (JSONException jsonException) {
                            // empty search on soap search
                        }
                    }

                    if (currentPersonAttribute.getString("name").equals("Nachname")) {

                        personName = currentPersonAttribute.getString("value") + ", ";
                    }

                    if (currentPersonAttribute.getString("name").equals("Vorname")) {

                        personName = personName + currentPersonAttribute.getString("value");
                    }
                }
                if (personIdentifier != null) {
                    autoCompleteObj.put(personName, personIdentifier);
                }
            }

            autocompleteOut.print(autoCompleteObj.toString());
            autocompleteOut.flush();

        } catch (Exception exception) {

            throw new ServletException(exception.getMessage());

        }
    }

    private String buildRequest(String lastName) {

        Element xml = new Element("search");
        xml.addContent(new Element("object").setText("person"));
        Element expression = new Element("expression");
        xml.addContent(expression);

        Element column = new Element("column");
        expression.addContent(column);
        column.setAttribute("name", "personal.nachname");
        column.setAttribute("value", lastName);

        XMLOutputter xout = new XMLOutputter();
        xout.setFormat(Format.getCompactFormat().setEncoding(encoding));
        String request = xout.outputString(new Document(xml));

        LOGGER.debug("Request: " + request);
        return request;
    }
}
