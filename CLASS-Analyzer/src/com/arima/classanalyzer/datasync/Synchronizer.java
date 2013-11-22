package com.arima.classanalyzer.datasync;

import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.EngineService;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.IEngine;
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
     * Sends students' details to CLASS-Engine as JSON objects and calls the CLASS-Engine's "insertStudents" method.
     *
     * @param cStudentList List of CStudent objects to be synced with central CLASS database
     * @return A four digits string message code representing the status of the action.
     */
    public String pushStudents(List<CStudent> cStudentList) {

        JSONArray jsonCStudentList = new JSONArray();

        Iterator<CStudent> cStudentIterator = cStudentList.iterator();

        while (cStudentIterator.hasNext()) {
            CStudent cStudent = cStudentIterator.next();

            JSONObject jsonCStudent = new JSONObject();
            try {
                jsonCStudent.put(Constants.SCHOOL_NO, cStudent.getSchoolNo());
                jsonCStudent.put(Constants.ADDMISSION_NO, cStudent.getStudentAdmissionNo());
                jsonCStudent.put(Constants.GENDER, cStudent.getGender());
                jsonCStudent.put(Constants.FROM, cStudent.getFrom());
                jsonCStudent.put(Constants.TO, cStudent.getTo());
                jsonCStudent.put(Constants.RELIGION, cStudent.getReligion());
                jsonCStudent.put(Constants.FATHER, cStudent.getFather());
                jsonCStudent.put(Constants.MOTHER, cStudent.getMother());
                jsonCStudent.put(Constants.NO_OF_SIBLINGS, cStudent.getNoOfSiblings());

                jsonCStudentList.put(jsonCStudent);

            } catch (JSONException e) {
                return Constants.ERROR_JSON;
            }

        }
        EngineService engineService = new EngineService();
        IEngine engine = engineService.getEnginePort();
        return engine.insertStudents(jsonCStudentList.toString());
    }

    /**
     * Sends exam details and the students' performance to CLASS-Engine as JSON objects
     * and calls the CLASS-Engine's "insertExamPerformance" method.
     *
     * @param cExam An instance of CExam type containing exam details and list of student performance to the exam.
     * @return A four digits string message code representing the status of the action.
     */
    public String pushExamPerformance(CExam cExam) {

        JSONObject jsonCExam = new JSONObject();
        try {
            jsonCExam.put(Constants.SCHOOL_NO, cExam.getSchoolNo());
            jsonCExam.put(Constants.EXAM_DATE, cExam.getDate());
            jsonCExam.put(Constants.EXAM_GRADE, cExam.getGrade());
            jsonCExam.put(Constants.EXAM_DIVISION, cExam.getDivision());
            jsonCExam.put(Constants.EXAM_TERM, cExam.getTerm());
            jsonCExam.put(Constants.EXAM_SUBJECT_ID, cExam.getSubjectId());
            jsonCExam.put(Constants.EXAM_TYPE, cExam.getExamType());

            JSONArray jsonPerformanceList = new JSONArray();

            List performanceList;
            if (cExam.getExamType() == Constants.GENERAL_EXAM) {
                performanceList = cExam.getcResultsList();
                Iterator performanceIterator = performanceList.iterator();
                while (performanceIterator.hasNext()) {
                    JSONObject jsonPerformance = new JSONObject();
                    CResults cResults = (CResults) performanceIterator.next();
                    jsonPerformance.put(Constants.ADDMISSION_NO, cResults.getAdmissionNo());
                    jsonPerformance.put(Constants.EXAM_RESULTS, cResults.getResult());
                    jsonPerformanceList.put(jsonPerformance);
                }
            } else {
                performanceList = cExam.getcMarksList();
                Iterator performanceIterator = performanceList.iterator();
                while (performanceIterator.hasNext()) {
                    JSONObject jsonPerformance = new JSONObject();
                    CMarks cMarks = (CMarks) performanceIterator.next();
                    jsonPerformance.put(Constants.ADDMISSION_NO, cMarks.getAdmissionNo());
                    jsonPerformance.put(Constants.EXAM_MARKS, cMarks.getMarks());
                    jsonPerformanceList.put(jsonPerformance);
                }
            }
            jsonCExam.put(Constants.PERFORMANCE_LIST, jsonPerformanceList);
        } catch (JSONException e) {
            return Constants.ERROR_JSON;
        }

        EngineService engineService = new EngineService();
        IEngine engine = engineService.getEnginePort();
        return engine.insertExamPerformance(jsonCExam.toString());

    }
}
