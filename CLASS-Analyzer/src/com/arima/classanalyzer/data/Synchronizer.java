package com.arima.classanalyzer.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
 * Date: 11/15/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class Synchronizer {

    /**
     * Sends students to CLASS-Engine as JSON objects and calls the CLASS-Engine's insertStudents method.
     *
     * @param cStudentList List of CStudent objects to be synced with central CLASS database
     * @return  A four digits string message code representing the status of the action.
     * @throws JSONException
     */
    public String pushStudents(List<CStudent> cStudentList) throws JSONException {

        JSONArray jsonCStudentList = new JSONArray();

        Iterator<CStudent> cStudentIterator = cStudentList.iterator();

        while (cStudentIterator.hasNext()) {
            CStudent cStudent = cStudentIterator.next();

            JSONObject jsonCStudent = new JSONObject();
            jsonCStudent.put("schoolNo", cStudent.getSchoolNo());
            jsonCStudent.put("studentAdmissionNo", cStudent.getStudentAdmissionNo());
            jsonCStudent.put("gender", cStudent.getGender());
            jsonCStudent.put("from", cStudent.getFrom());
            jsonCStudent.put("to", cStudent.getTo());
            jsonCStudent.put("religion", cStudent.getReligion());
            jsonCStudent.put("father", cStudent.getFather());
            jsonCStudent.put("mother", cStudent.getMother());
            jsonCStudent.put("noOfSiblings", cStudent.getNoOfSiblings());

            jsonCStudentList.put(jsonCStudent);
        }
        //uncomment after add webservice method in Engine.
        //return Engine.insertStudents(jsonCStudentList.toString());
          return null;
    }
}
