package com.arima.classanalyzer.data;

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

    public String SyncStudents(List<CStudent> cStudentList) throws JSONException {

        JSONObject jsonCStudentList = new JSONObject();
        int noOfstudents = 0;
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

            noOfstudents++;
            jsonCStudentList.put(String.valueOf(noOfstudents), jsonCStudent);
        }
         // have
        //return Engine.updateStudents(jsonCStudentList);
          return null;
    }
}
