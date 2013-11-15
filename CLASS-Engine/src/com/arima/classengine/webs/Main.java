package com.arima.classengine.webs;

import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: Dell
 * Date: 11/7/13
 * Time: 1:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7070/class?wsdl",new Engine());
    }
}
