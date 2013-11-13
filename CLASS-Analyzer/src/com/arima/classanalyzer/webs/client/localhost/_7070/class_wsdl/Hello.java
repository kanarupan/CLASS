
package com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl;

import com.arima.classanalyzer.webs.client.ObjectFactory;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 */
@WebService(name = "Hello", targetNamespace = "http://webs.classengine.arima.com/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface Hello {


    /**
     * @return returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getmodel", targetNamespace = "http://webs.classengine.arima.com/", className = "com.arima.classengine.webs.Getmodel")
    @ResponseWrapper(localName = "getmodelResponse", targetNamespace = "http://webs.classengine.arima.com/", className = "com.arima.classengine.webs.GetmodelResponse")
    @Action(input = "http://webs.classengine.arima.com/Hello/getmodelRequest", output = "http://webs.classengine.arima.com/Hello/getmodelResponse")
    public String getmodel();

}
