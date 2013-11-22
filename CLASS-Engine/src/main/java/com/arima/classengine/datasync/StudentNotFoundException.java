package com.arima.classengine.datasync;

/**
 * Created with IntelliJ IDEA.
 * User: jaykrish
 * Date: 11/21/13
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentNotFoundException extends Exception {

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(int schoolNo, String admissionNo) {
        super("The Student with admission number " + admissionNo + " and school number " + String.valueOf(schoolNo) + " not found in the CLASS server");
    }
}
