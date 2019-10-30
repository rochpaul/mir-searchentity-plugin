/**
 * SoapsearchSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mycore.searchentity.plugin.soap;

public class SoapsearchSoapBindingSkeleton implements org.mycore.searchentity.plugin.soap.SOAPSearch, org.apache.axis.wsdl.Skeleton {
    private org.mycore.searchentity.plugin.soap.SOAPSearch impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "searchXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("search", _params, new javax.xml.namespace.QName("", "searchReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://soap.his.de", "search"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("search") == null) {
            _myOperations.put("search", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("search")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "searchXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getForm", _params, new javax.xml.namespace.QName("", "getFormReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://soap.his.de", "getForm"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getForm") == null) {
            _myOperations.put("getForm", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getForm")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "searchXML"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getForm4AuthUser", _params, new javax.xml.namespace.QName("", "getForm4AuthUserReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://soap.his.de", "getForm4AuthUser"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getForm4AuthUser") == null) {
            _myOperations.put("getForm4AuthUser", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getForm4AuthUser")).add(_oper);
    }

    public SoapsearchSoapBindingSkeleton() {
        this.impl = new org.mycore.searchentity.plugin.soap.SoapsearchSoapBindingImpl();
    }

    public SoapsearchSoapBindingSkeleton(org.mycore.searchentity.plugin.soap.SOAPSearch impl) {
        this.impl = impl;
    }
    public java.lang.String search(java.lang.String searchXML) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.search(searchXML);
        return ret;
    }

    public java.lang.String getForm(java.lang.String searchXML) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getForm(searchXML);
        return ret;
    }

    public java.lang.String getForm4AuthUser(java.lang.String searchXML) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getForm4AuthUser(searchXML);
        return ret;
    }

}
