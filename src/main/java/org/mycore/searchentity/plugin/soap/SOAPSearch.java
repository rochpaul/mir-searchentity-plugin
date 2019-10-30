/**
 * SOAPSearch.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mycore.searchentity.plugin.soap;

public interface SOAPSearch extends java.rmi.Remote {
    public java.lang.String search(java.lang.String searchXML) throws java.rmi.RemoteException;
    public java.lang.String getForm(java.lang.String searchXML) throws java.rmi.RemoteException;
    public java.lang.String getForm4AuthUser(java.lang.String searchXML) throws java.rmi.RemoteException;
}
