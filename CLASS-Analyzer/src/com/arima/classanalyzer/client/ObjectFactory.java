
package com.arima.classanalyzer.webs.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.arima.classengine.webs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetmodelResponse_QNAME = new QName("http://webs.classengine.arima.com/", "getmodelResponse");
    private final static QName _Getmodel_QNAME = new QName("http://webs.classengine.arima.com/", "getmodel");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.arima.classengine.webs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetmodelResponse }
     * 
     */
    public GetmodelResponse createGetmodelResponse() {
        return new GetmodelResponse();
    }

    /**
     * Create an instance of {@link Getmodel }
     * 
     */
    public Getmodel createGetmodel() {
        return new Getmodel();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetmodelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "getmodelResponse")
    public JAXBElement<GetmodelResponse> createGetmodelResponse(GetmodelResponse value) {
        return new JAXBElement<GetmodelResponse>(_GetmodelResponse_QNAME, GetmodelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link Getmodel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "getmodel")
    public JAXBElement<Getmodel> createGetmodel(Getmodel value) {
        return new JAXBElement<Getmodel>(_Getmodel_QNAME, Getmodel.class, null, value);
    }

}
