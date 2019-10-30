package org.mycore.searchentity.plugin.soap;

public class SOAPSearchProxy implements org.mycore.searchentity.plugin.soap.SOAPSearch {
  private String _endpoint = null;
  private org.mycore.searchentity.plugin.soap.SOAPSearch sOAPSearch = null;
  
  public SOAPSearchProxy() {
    _initSOAPSearchProxy();
  }
  
  public SOAPSearchProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPSearchProxy();
  }
  
  private void _initSOAPSearchProxy() {
    try {
      sOAPSearch = (new org.mycore.searchentity.plugin.soap.SOAPSearchServiceLocator()).getsoapsearch();
      if (sOAPSearch != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPSearch)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPSearch)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPSearch != null)
      ((javax.xml.rpc.Stub)sOAPSearch)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.mycore.searchentity.plugin.soap.SOAPSearch getSOAPSearch() {
    if (sOAPSearch == null)
      _initSOAPSearchProxy();
    return sOAPSearch;
  }
  
  public java.lang.String search(java.lang.String searchXML) throws java.rmi.RemoteException{
    if (sOAPSearch == null)
      _initSOAPSearchProxy();
    return sOAPSearch.search(searchXML);
  }
  
  public java.lang.String getForm(java.lang.String searchXML) throws java.rmi.RemoteException{
    if (sOAPSearch == null)
      _initSOAPSearchProxy();
    return sOAPSearch.getForm(searchXML);
  }
  
  public java.lang.String getForm4AuthUser(java.lang.String searchXML) throws java.rmi.RemoteException{
    if (sOAPSearch == null)
      _initSOAPSearchProxy();
    return sOAPSearch.getForm4AuthUser(searchXML);
  }
  
  
}