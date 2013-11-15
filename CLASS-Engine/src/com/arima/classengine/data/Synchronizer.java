package com.arima.classengine.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
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
        JSONArray jsonCStudentList = new JSONArray();


        JSONObject jsonCStudent = new JSONObject();
        jsonCStudent.put("schoolNo", 1);
        jsonCStudent.put("studentAdmissionNo", "FIANLTEST");
        jsonCStudent.put("gender", "malefe");
        jsonCStudent.put("religion", "teriyaa");
        jsonCStudent.put("language", "teriyaa");
        jsonCStudent.put("father", "ammada husbend");
        jsonCStudent.put("mother", "appanda manisi");
        jsonCStudent.put("noOfSiblings", 11);

        jsonCStudentList.put(jsonCStudent);
        Synchronizer synchronizer = new Synchronizer();
        System.out.println(synchronizer.insertStudents(jsonCStudentList.toString()));

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
            JSONArray jsonCStudentList = new JSONArray(jsonCStudentListString);


            connection = CLASSJDBCConnector.openConnection();
            connection.setAutoCommit(false); //transaction block start
            for (int i = 0; i < jsonCStudentList.length(); i++) {

                JSONObject jsonCStudent = (JSONObject) jsonCStudentList.get(i);

                PreparedStatement statement = connection.prepareStatement(Constants.INSERT_STUDENT);

                statement.setInt(1, jsonCStudent.getInt(Constants.SCHOOL_NO));
                statement.setString(2, jsonCStudent.getString(Constants.ADDMISSION_NO));
                statement.setString(3, jsonCStudent.getString(Constants.GENDER));
                statement.setString(4, jsonCStudent.getString(Constants.RELIGION));
                statement.setString(5, jsonCStudent.getString(Constants.LANGUAGE));
                statement.setString(6, jsonCStudent.getString(Constants.FATHER));
                statement.setString(7, jsonCStudent.getString(Constants.MOTHER));
                statement.setInt(8, jsonCStudent.getInt(Constants.NO_OF_SIBLINGS));
                statement.executeUpdate();

            }  //for-loop ends
            connection.commit(); //transaction ends here
            connection.close();

        } catch (ClassNotFoundException e) {
            returnCode = Constants.ERROR_CLASS_NOT_FOUND;
        } catch (SQLException e) {
            connection.rollback();//if errors rollback to consistent stage
            connection.close();
            returnCode = Constants.ERROR_SQL;
        } catch (InstantiationException e) {
            returnCode = Constants.ERROR_INSTANTIATION;
        } catch (IllegalAccessException e) {
            returnCode = Constants.ERROR_ILLEGAL_ACCESS;
        } catch (JSONException e) {
            returnCode = Constants.ERROR_JSON;
        } catch (Exception e) {
            returnCode = Constants.ERROR_UNKNOWN;
        } finally {
            return returnCode;
        }
    }
}
