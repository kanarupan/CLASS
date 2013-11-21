package com.arima.classengine.datasync;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: jaykrish
 * Date: 11/15/13
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */

public class Synchronizer {

    Connection connection = null;

    /**
     * TODO: Change main to unittests.
     *
     * @param args
     * @throws JSONException
     */
    public static void main(String[] args) throws JSONException {
//        JSONArray jsonCStudentList = new JSONArray();
//
//        JSONObject jsonCStudent = new JSONObject();
//        jsonCStudent.put("schoolNo", 1);
//        jsonCStudent.put("studentAdmissionNo", "FIANLTEST");
//        jsonCStudent.put("gender", "malefe");
//        jsonCStudent.put("religion", "teriyaa");
//        jsonCStudent.put("language", "teriyaa");
//        jsonCStudent.put("father", "ammada husbend");
//        jsonCStudent.put("mother", "appanda manisi");
//        jsonCStudent.put("noOfSiblings", 11);
//
//        jsonCStudentList.put(jsonCStudent);
//        Synchronizer synchronizer = new Synchronizer();
//       // System.out.println(synchronizer.insertStudents(jsonCStudentList.toString()));
//
//
//        JSONObject jsonCExam = new JSONObject();
//        jsonCExam.put(Constants.SCHOOL_NO, 11086);
//        jsonCExam.put(Constants.EXAM_DATE, "2012-1-1");
//        jsonCExam.put(Constants.EXAM_GRADE, 11);
//        jsonCExam.put(Constants.EXAM_DIVISION, "COM");
//        jsonCExam.put(Constants.EXAM_TERM, 3);
//        jsonCExam.put(Constants.EXAM_SUBJECT_ID, 4);
//        jsonCExam.put(Constants.EXAM_TYPE, 2);
//
//        JSONArray jsonArray = new JSONArray();
//        JSONObject jsonObject1 = new JSONObject();
//        jsonObject1.put(Constants.ADDMISSION_NO, "18745");
//        jsonObject1.put(Constants.EXAM_MARKS, 56);
//        jsonArray.put(jsonObject1);
//        JSONObject jsonObject2 = new JSONObject();
//        jsonObject2.put(Constants.ADDMISSION_NO, "18746");
//        jsonObject2.put(Constants.EXAM_MARKS, 100);
//        jsonArray.put(jsonObject2);
//        jsonCExam.put(Constants.PERFORMANCE_LIST, jsonArray);
//
////        jsonCExam.put("mother", "appanda manisi");
////        jsonCExam.put("noOfSiblings", 11);
//
//
//        System.out.println(synchronizer.insertExamPerformance(jsonCExam.toString()));

    }

    /**
     * Insert given students into CLASS Student table.
     *
     * @param jsonCStudentListString A String of JSONArray containing collection of JSONObjects of CStudents.
     * @return A four digits string message code representing the status of the action.
     */
    public String insertStudents(String jsonCStudentListString) {

        String returnCode = Constants.SUCCESS_MSG;
        try {
            connection = CLASSJDBCConnector.openConnection();
            connection.setAutoCommit(false); //transaction block start

            JSONArray jsonCStudentList = new JSONArray(jsonCStudentListString);

            for (int i = 0; i < jsonCStudentList.length(); i++) {

                JSONObject jsonCStudent = (JSONObject) jsonCStudentList.get(i);

                PreparedStatement insertStudentStatement = connection.prepareStatement(Constants.INSERT_STUDENT);

                insertStudentStatement.setInt(1, jsonCStudent.getInt(Constants.SCHOOL_NO));
                insertStudentStatement.setString(2, jsonCStudent.getString(Constants.ADDMISSION_NO));
                insertStudentStatement.setString(3, jsonCStudent.getString(Constants.GENDER));
                insertStudentStatement.setString(4, jsonCStudent.getString(Constants.RELIGION));
                insertStudentStatement.setString(5, jsonCStudent.getString(Constants.LANGUAGE));
                insertStudentStatement.setString(6, jsonCStudent.getString(Constants.FATHER));
                insertStudentStatement.setString(7, jsonCStudent.getString(Constants.MOTHER));
                insertStudentStatement.setInt(8, jsonCStudent.getInt(Constants.NO_OF_SIBLINGS));
                insertStudentStatement.executeUpdate();

            }  //for-loop ends
            connection.commit(); //transaction ends here
            connection.close();

        } catch (ClassNotFoundException e) {
            connection.rollback();//if errors rollback to consistent stage
            connection.close();
            returnCode = Constants.ERROR_CLASS_NOT_FOUND + e.toString();
        } catch (SQLException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_SQL + e.toString();
        } catch (InstantiationException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_INSTANTIATION + e.toString();
        } catch (IllegalAccessException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_ILLEGAL_ACCESS + e.toString();
        } catch (JSONException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_JSON + e.toString();
        } catch (Exception e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_UNKNOWN + e.toString();
        } finally {
            return returnCode;
        }
    }

    /**
     * Insert given exam and corresponding students' performance to CLASS database.
     * If exam type is general then it is expected the performance to be results as string.
     * Otherwise, for term exam and continuous assignments performance could be marks as Integer.
     *
     * @param jsonCExamString A String of JSONObject containing exam details and collection of JSONObjects of CPerformance.
     * @return A four digits string message code representing the status of the action.
     */
    public String insertExamPerformance(String jsonCExamString) {

        String returnCode = Constants.SUCCESS_MSG;
        try {
            connection = CLASSJDBCConnector.openConnection();
            connection.setAutoCommit(false); //transaction block start.

            JSONObject jsonCExam = new JSONObject(jsonCExamString);

            PreparedStatement insertExamStatement = connection.prepareStatement(Constants.INSERT_EXAM, Statement.RETURN_GENERATED_KEYS);
            int schoolNo = jsonCExam.getInt(Constants.SCHOOL_NO);
            insertExamStatement.setInt(1, schoolNo);
            insertExamStatement.setString(2, jsonCExam.getString(Constants.EXAM_DATE));
            insertExamStatement.setInt(3, jsonCExam.getInt(Constants.EXAM_GRADE));
            insertExamStatement.setString(4, jsonCExam.getString(Constants.EXAM_DIVISION));
            insertExamStatement.setInt(5, jsonCExam.getInt(Constants.EXAM_TERM));
            insertExamStatement.setInt(6, jsonCExam.getInt(Constants.EXAM_SUBJECT_ID));
            insertExamStatement.setInt(7, jsonCExam.getInt(Constants.EXAM_TYPE));
            insertExamStatement.executeUpdate();
            ResultSet rsExamKey = insertExamStatement.getGeneratedKeys();
            rsExamKey.next();
            int examId = rsExamKey.getInt(1);


            JSONArray jsonPerformanceList = jsonCExam.getJSONArray(Constants.PERFORMANCE_LIST);

            /* If general exam enter results into results table */
            Boolean isGeneralExam = false;
            if (jsonCExam.getInt(Constants.EXAM_TYPE) == Constants.GENERAL_EXAM) {
                isGeneralExam = true;
            }

            for (int i = 0; i < jsonPerformanceList.length(); i++) {
                String results =null;
                int marks = 0;
                int studentId = -1;
                PreparedStatement insertStudentPerformanceStatement = null;
                int studentPerformanceId;
                PreparedStatement insertPerformanceStatement = null;

                JSONObject jsonPerformance = (JSONObject) jsonPerformanceList.get(i);
                String admissionNo = jsonPerformance.getString(Constants.ADDMISSION_NO);

                if (isGeneralExam) {
                    results = jsonPerformance.getString(Constants.EXAM_RESULTS);
                } else {
                    marks = jsonPerformance.getInt(Constants.EXAM_MARKS);
                }

                PreparedStatement selectStudentStatement = connection.prepareStatement(Constants.SELECT_STUDENTID);
                selectStudentStatement.setInt(1, schoolNo);
                selectStudentStatement.setString(2, admissionNo);
                ResultSet studentRs = selectStudentStatement.executeQuery();
                while (studentRs.next()) {
                    studentId = studentRs.getInt(Constants.STUDENTIDCOL);
                }
                if (studentId == -1) {
                    throw new StudentNotFoundException(schoolNo, admissionNo);
                }

                /*Enter student peformance table entries*/
                insertStudentPerformanceStatement = connection.prepareStatement(Constants.INSERT_STUDENT_PERFORMANCE, Statement.RETURN_GENERATED_KEYS);
                insertStudentPerformanceStatement.setInt(1, studentId);
                insertStudentPerformanceStatement.setInt(2, Constants.PERFORMANCE_TYPE_EXAM);
                insertStudentPerformanceStatement.executeUpdate();
                ResultSet rsStudentResultPerformanceKey = insertStudentPerformanceStatement.getGeneratedKeys();
                rsStudentResultPerformanceKey.next();
                studentPerformanceId = rsStudentResultPerformanceKey.getInt(1);

                 if(isGeneralExam){
                 /*Enter Resutls table entries*/
                 insertPerformanceStatement = connection.prepareStatement(Constants.INSERT_RESULTS, Statement.RETURN_GENERATED_KEYS);
                     insertPerformanceStatement.setString(2, results);
                 }
                else{
                     /* If term or CA exam enter marks into marks table*/
                     insertPerformanceStatement = connection.prepareStatement(Constants.INSERT_MARKS, Statement.RETURN_GENERATED_KEYS);
                     insertPerformanceStatement.setInt(2, marks);
                 }
                insertPerformanceStatement.setInt(1, studentPerformanceId);
                insertPerformanceStatement.setInt(3, examId);
                insertPerformanceStatement.executeUpdate();
            }  //for-loop ends

            connection.commit(); //transaction ends here
            connection.close();

        } catch (ClassNotFoundException e) {
            connection.rollback();//if errors rollback to consistent stage
            connection.close();
            returnCode = Constants.ERROR_CLASS_NOT_FOUND + e.toString();
        } catch (SQLException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_SQL + e.toString();
        } catch (InstantiationException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_INSTANTIATION + e.toString();
        } catch (IllegalAccessException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_ILLEGAL_ACCESS + e.toString();
        } catch (JSONException e) {
            connection.rollback();
            connection.close();
            System.out.println(e.toString());
            returnCode = Constants.ERROR_JSON + e.toString();
        } catch (StudentNotFoundException e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_NO_STUDENT + e.toString();
        } catch (Exception e) {
            connection.rollback();
            connection.close();
            returnCode = Constants.ERROR_UNKNOWN + e.toString();
        } finally {
            return returnCode;
        }
    }

}
