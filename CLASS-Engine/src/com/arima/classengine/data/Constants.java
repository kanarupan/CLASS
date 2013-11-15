package com.arima.classengine.data;

/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
 * Date: 11/15/13
 * Time: 1:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    /**
     * CLASS Database connection strings.
     */
    public static final String CLASS_DB_NAME = "jdbc:mysql://localhost:3306/class";
    public static final String CLASS_DB_USERNAME = "root";
    public static final String CLASS_DB_PASSWORD= "";

    public static final String INSERT_STUDENT = "INSERT INTO student (school_no, student_school_id, gender, religion, language, father, mother, siblings) VALUES (?,?,?,?,?,?,?,?)";

    /**
     * Sync Success/Error messages codes.
     */
    public static final String SUCCESS_MSG = "SD01";
    public static final String ERROR_CLASS_NOT_FOUND = "ED01";
    public static final String ERROR_SQL = "ED02";
    public static final String ERROR_INSTANTIATION = "ED03";
    public static final String ERROR_ILLEGAL_ACCESS = "ED04";
    public static final String ERROR_UNKNOWN = "ED05";
    public static final String WARNING_MSG= "WD01";

    /**
     * JSON keys
     */
    public static final String SCHOOL_NO = "schoolNo";
    public static final String ADDMISSION_NO = "studentAdmissionNo";
    public static final String GENDER = "gender";
    public static final String RELIGION = "religion";
    public static final String LANGUAGE = "language";
    public static final String FATHER = "father";
    public static final String MOTHER = "mother";
    public static final String NO_OF_SIBLINGS = "noOfSiblings";


}
