package org.mycore.searchentity.plugin.servlets;

import org.mycore.common.MCRException;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;
import org.mycore.searchentity.plugin.soap.client.SOAPSearch;
import org.mycore.searchentity.plugin.soap.client.SOAPSearchServiceLocator;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
    private static final int maxSize = 30;

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

    }

    @Override
    protected void doGetPost(MCRServletJob job) throws Exception {

        HttpServletRequest request = job.getRequest();

        String searchParam = (String) request.getParameter("searchParam");
        if (searchParam == null || searchParam.equals(""))
            throw new ServletException("Invalid or non-existent lsfId parameter in SendXml servlet.");

        HttpServletResponse response = job.getResponse();

        ServletOutputStream stream = null;
        BufferedInputStream buf = null;

        JSONObject soapsearchData = null;
        String requestSoap = buildRequest(searchParam);

        try {

            stream = response.getOutputStream();
            response.setContentType("application/json");

            soapsearchData = XML.toJSONObject(soapsearch.search(requestSoap));

            JSONArray resultObjects = soapsearchData.getJSONObject("result")
                .getJSONObject("success")
                .getJSONObject("list")
                .getJSONArray("object");

            int currentSize = resultObjects.length() <= maxSize ? resultObjects.length() : maxSize;

            LOGGER.info(
                "Found " + resultObjects.length() + " result object Entries for request " + requestSoap + ": First "
                    + currentSize + " will be used for autocomplete");

            for (int ind = 0; ind < currentSize; ind++) {

                JSONArray currentPersonMin = resultObjects.getJSONObject(ind).getJSONArray("attribute");

                for (int indPerson = 0; indPerson < currentPersonMin.length(); indPerson++) {

                    JSONObject currentPersonAttribute = currentPersonMin.getJSONObject(indPerson);
                }
            }

        } catch (IOException ioe) {

            throw new ServletException(ioe.getMessage());

        } finally {

            if (stream != null)
                stream.close();
            if (buf != null)
                buf.close();
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
