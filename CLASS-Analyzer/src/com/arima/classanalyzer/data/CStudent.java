package com.arima.classanalyzer.data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
 * Date: 11/15/13
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class CStudent {
    int schoolNo;
    String studentAdmissionNo;
    String gender;
    Date from;
    Date to;
    String religion;
    String father;
    String mother;
    int noOfSiblings;

    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getStudentAdmissionNo() {
        return studentAdmissionNo;
    }

    public void setStudentAdmissionNo(String studentAdmissionNo) {
        this.studentAdmissionNo = studentAdmissionNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public int getNoOfSiblings() {
        return noOfSiblings;
    }

    public void setNoOfSiblings(int noOfSiblings) {
        this.noOfSiblings = noOfSiblings;
    }
}
