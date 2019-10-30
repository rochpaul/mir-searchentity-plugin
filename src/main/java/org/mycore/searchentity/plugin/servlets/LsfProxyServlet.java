package org.mycore.searchentity.plugin.servlets;

import org.mycore.common.MCRException;
import org.mycore.frontend.servlets.MCRServlet;
import org.mycore.frontend.servlets.MCRServletJob;
import org.mycore.searchentity.plugin.soap.SOAPSearch;
import org.mycore.searchentity.plugin.soap.SOAPSearchServiceLocator;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class HelloWorldServlet
 */
public class LsfProxyServlet extends MCRServlet {
	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LogManager.getLogger(LsfProxyServlet.class);

	/** The client instance of LSF SOAPSearch web service */
	protected SOAPSearch soapsearch;

	/** The client instance of LSF DBInterface web service */
//    protected DBInterface dbinterface;

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

		String lsfId = (String) request.getParameter("lsfId");
		if (lsfId == null || lsfId.equals(""))
			throw new ServletException("Invalid or non-existent lsfId parameter in SendXml servlet.");

		HttpServletResponse response = job.getResponse();

		ServletOutputStream stream = null;
		BufferedInputStream buf = null;

		try {

			stream = response.getOutputStream();

			response.setContentType("application/json");

		} catch (IOException ioe) {

			throw new ServletException(ioe.getMessage());

		} finally {

			if (stream != null)
				stream.close();
			if (buf != null)
				buf.close();
		}

	}
}
