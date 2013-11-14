package com.arima.classengine.webs;

import com.arima.classengine.data.ExamSync;
import org.json.JSONException;

import javax.jws.WebService;

/**
 * Created with IntelliJ IDEA.
 * User: Dell
 * Date: 11/7/13
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.arima.classengine.webs.Hello",targetNamespace = "http://localhost:7070/class?wsdl")
public class HelloClass implements Hello{

	public String getmodel() {
		// TODO Auto-generated method stub
        ExamSync examSync=new ExamSync();

        try {
            return examSync.getjson();
        } catch (JSONException e) {
            return "hoho not working:(";
        }
    }


}
