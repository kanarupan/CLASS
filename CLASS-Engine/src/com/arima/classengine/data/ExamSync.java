package com.arima.classengine.data;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
 * Date: 11/9/13
 * Time: 2:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExamSync {
    public String getjson() throws JSONException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("data","fun haha..!!!") ;
        return jsonObj.toString();
    }
}
