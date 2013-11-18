package com.arima.classengine.webs;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell
 * Date: 11/7/13
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IEngine {

    /**
     * Insert given students into CLASS Student table.
     *
     * @param jsonCStudentListString A String of JSONArray containing collection of JSONObjects of CStudents.
     * @return A four digits string message code representing the status of the action.
     *
     */
    @WebMethod
    public String insertStudents(String jsonCStudentListString);

    /**
     *
     * @return
     */
    @WebMethod
    public String getmodel();

    /**
     * find nearest profiles within the given school
     *
     * @param schoolNo the school number
     * @param grade the grade
     * @param term the term
     * @param subjects the list of subjects to consider
     * @param marks the list of marks for the student
     * @return the list of nearest profiles' index numbers
     */
    @WebMethod
    public ArrayList<Integer> getNearestLocalProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks);

    /**
     * find nearest profiles globally
     *
     * @param grade the grade
     * @param term the term
     * @param subjects the list of subjects to consider
     * @param marks the list of marks for the student
     * @return the list of nearest profiles' index numbers
     */
    @WebMethod
    public ArrayList<Integer> getNearestGlobalProfiles(int grade, int term, List<String> subjects, List<Integer> marks);



}
