package org.mycore.searchentity.plugin.soap.client;

public class DBInterfaceProxy implements org.mycore.searchentity.plugin.soap.client.DBInterface {
  private String _endpoint = null;
  private org.mycore.searchentity.plugin.soap.client.DBInterface dBInterface = null;
  
  public DBInterfaceProxy() {
    _initDBInterfaceProxy();
  }
  
  public DBInterfaceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDBInterfaceProxy();
  }
  
  private void _initDBInterfaceProxy() {
    try {
      dBInterface = (new org.mycore.searchentity.plugin.soap.client.DBInterfaceServiceLocator()).getdbinterface();
      if (dBInterface != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dBInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dBInterface)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dBInterface != null)
      ((javax.xml.rpc.Stub)dBInterface)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.mycore.searchentity.plugin.soap.client.DBInterface getDBInterface() {
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface;
  }
  
  public java.lang.String getDataByParams(java.lang.String strName, java.lang.String xmlParams) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.getDataByParams(strName, xmlParams);
  }
  
  public java.lang.String getDataByParams(java.lang.String strName, java.lang.String strID, java.lang.String xmlParams) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.getDataByParams(strName, strID, xmlParams);
  }
  
  public java.lang.String getDataXML(java.lang.String xmlParams) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.getDataXML(xmlParams);
  }
  
  public java.lang.String getDataXMLByAuthUser(java.lang.String xmlParams) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.getDataXMLByAuthUser(xmlParams);
  }
  
  public java.lang.String getDataSearch(java.lang.String elParams) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.getDataSearch(elParams);
  }
  
  public java.lang.String SOAPDataImporter(java.lang.String strXMLStructure, java.lang.String strUser, java.lang.String strPassword) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.SOAPDataImporter(strXMLStructure, strUser, strPassword);
  }
  
  public java.lang.String process(java.lang.String system, java.lang.String body) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.process(system, body);
  }
  
  public java.lang.String process(java.lang.String system, java.lang.String user, java.lang.String pass, java.lang.String body) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.process(system, user, pass, body);
  }
  
  public java.lang.String getData(java.lang.String strName, java.lang.String strID) throws java.rmi.RemoteException{
    if (dBInterface == null)
      _initDBInterfaceProxy();
    return dBInterface.getData(strName, strID);
  }
  
  
}