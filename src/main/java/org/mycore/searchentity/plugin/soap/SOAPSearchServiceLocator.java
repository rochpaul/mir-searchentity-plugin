/**
 * SOAPSearchServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mycore.searchentity.plugin.soap;

public class SOAPSearchServiceLocator extends org.apache.axis.client.Service implements org.mycore.searchentity.plugin.soap.SOAPSearchService {

    public SOAPSearchServiceLocator() {
    }


    public SOAPSearchServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SOAPSearchServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for soapsearch
    private java.lang.String soapsearch_address = "http://www.lsf.uni-due.de/lsf/services/soapsearch";

    public java.lang.String getsoapsearchAddress() {
        return soapsearch_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String soapsearchWSDDServiceName = "soapsearch";

    public java.lang.String getsoapsearchWSDDServiceName() {
        return soapsearchWSDDServiceName;
    }

    public void setsoapsearchWSDDServiceName(java.lang.String name) {
        soapsearchWSDDServiceName = name;
    }

    public org.mycore.searchentity.plugin.soap.SOAPSearch getsoapsearch() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(soapsearch_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsoapsearch(endpoint);
    }

    public org.mycore.searchentity.plugin.soap.SOAPSearch getsoapsearch(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.mycore.searchentity.plugin.soap.SoapsearchSoapBindingStub _stub = new org.mycore.searchentity.plugin.soap.SoapsearchSoapBindingStub(portAddress, this);
            _stub.setPortName(getsoapsearchWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsoapsearchEndpointAddress(java.lang.String address) {
        soapsearch_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.mycore.searchentity.plugin.soap.SOAPSearch.class.isAssignableFrom(serviceEndpointInterface)) {
                org.mycore.searchentity.plugin.soap.SoapsearchSoapBindingStub _stub = new org.mycore.searchentity.plugin.soap.SoapsearchSoapBindingStub(new java.net.URL(soapsearch_address), this);
                _stub.setPortName(getsoapsearchWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("soapsearch".equals(inputPortName)) {
            return getsoapsearch();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.lsf.uni-due.de/lsf/services/soapsearch", "SOAPSearchService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.lsf.uni-due.de/lsf/services/soapsearch", "soapsearch"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("soapsearch".equals(portName)) {
            setsoapsearchEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
