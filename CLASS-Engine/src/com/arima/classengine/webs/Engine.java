package com.arima.classengine.webs;

import com.arima.classengine.data.ExamSync;
import com.arima.classengine.engine.Analyzer;
import org.json.JSONException;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell
 * Date: 11/7/13
 * Time: 1:34 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "com.arima.classengine.webs.IEngine", targetNamespace = "http://localhost:7070/class?wsdl")
public class Engine implements IEngine {

    public String getmodel() {
        ExamSync examSync = new ExamSync();

        try {
            return examSync.getjson();
        } catch (JSONException e) {
            return "Sorry, couldn't make it at the moment. We'll look into ASAP!";
        }
    }

    public ArrayList<Integer> getNearestLocalProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) {
        try {
            return Analyzer.getNearestProfiles(schoolNo, grade, term, subjects, marks);
        } catch (Exception e) {
            return null;


        }
    }

    public ArrayList<Integer> getNearestGlobalProfiles(int grade, int term, List<String> subjects, List<Integer> marks) {
        try {
            return Analyzer.getNearestProfiles(grade, term, subjects, marks);
        } catch (Exception e) {
            return null;


        }

    }


}
