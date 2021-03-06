
package com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EngineService", targetNamespace = "http://localhost:7070/class?wsdl", wsdlLocation = "http://localhost:7070/class?wsdl")
public class EngineService
    extends Service
{

    private final static URL ENGINESERVICE_WSDL_LOCATION;
    private final static WebServiceException ENGINESERVICE_EXCEPTION;
    private final static QName ENGINESERVICE_QNAME = new QName("http://localhost:7070/class?wsdl", "EngineService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:7070/class?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ENGINESERVICE_WSDL_LOCATION = url;
        ENGINESERVICE_EXCEPTION = e;
    }

    public EngineService() {
        super(__getWsdlLocation(), ENGINESERVICE_QNAME);
    }

    public EngineService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENGINESERVICE_QNAME, features);
    }

    public EngineService(URL wsdlLocation) {
        super(wsdlLocation, ENGINESERVICE_QNAME);
    }

    public EngineService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENGINESERVICE_QNAME, features);
    }

    public EngineService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EngineService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IEngine
     */
    @WebEndpoint(name = "EnginePort")
    public IEngine getEnginePort() {
        return super.getPort(new QName("http://localhost:7070/class?wsdl", "EnginePort"), IEngine.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IEngine
     */
    @WebEndpoint(name = "EnginePort")
    public IEngine getEnginePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost:7070/class?wsdl", "EnginePort"), IEngine.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENGINESERVICE_EXCEPTION!= null) {
            throw ENGINESERVICE_EXCEPTION;
        }
        return ENGINESERVICE_WSDL_LOCATION;
    }

}
