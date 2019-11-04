package org.mycore.searchentity.plugin.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.mycore.common.MCRException;
import org.mycore.searchentity.plugin.soap.client.DBInterface;
import org.mycore.searchentity.plugin.soap.client.DBInterfaceServiceLocator;
import org.mycore.searchentity.plugin.soap.client.SOAPSearch;
import org.mycore.searchentity.plugin.soap.client.SOAPSearchServiceLocator;

public class SimpleLsfUse {

    private static final long serialVersionUID = 1L;

    private static Logger LOGGER = LogManager.getLogger(SimpleLsfUse.class);

    /** The client instance of LSF SOAPSearch web service */
    private SOAPSearch soapsearch;

    /** The client instance of LSF DBInterface web service */
    private DBInterface dbinterface;

    /** Encoding of XML Strings */
    private final static String encoding = "ISO-8859-15";

    public SimpleLsfUse() {
        LOGGER.info("Initializing client instance of LSF SOAPSearch web service");

        try {
            soapsearch = new SOAPSearchServiceLocator().getsoapsearch();
            dbinterface = new DBInterfaceServiceLocator().getdbinterface();

        } catch (Exception ex) {

            String msg = "Could not locate HIS LSF WebService";
            throw new MCRException(msg, ex);
        }
    }

    private void doSimpleLsfUse() {

        String request = buildRequest("Lützenkirchen");
        String responseSoapSearch;

        try {
            responseSoapSearch = soapsearch.search(request);

            String responseDetail = dbinterface.getData("PersonDetail", "11775");

            System.out.println(responseSoapSearch.toString());
            System.out.println(responseDetail.toString());
        } catch (java.rmi.RemoteException remoteException) {
            System.out.println(remoteException);
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

    public static void main(String[] args) {

        SimpleLsfUse simple = new SimpleLsfUse();
        simple.doSimpleLsfUse();

        System.out.println(simple.toString());
    }
}
