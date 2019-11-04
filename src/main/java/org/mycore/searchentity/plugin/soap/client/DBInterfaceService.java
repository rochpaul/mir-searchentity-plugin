/**
 * DBInterfaceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.mycore.searchentity.plugin.soap.client;

public interface DBInterfaceService extends javax.xml.rpc.Service {
    public java.lang.String getdbinterfaceAddress();

    public org.mycore.searchentity.plugin.soap.client.DBInterface getdbinterface() throws javax.xml.rpc.ServiceException;

    public org.mycore.searchentity.plugin.soap.client.DBInterface getdbinterface(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
