package com.arima.classanalyzer.datasync;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jayrksih
 * Date: 11/20/13
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class CExam {
    private int schoolNo;
    private Date date;
    private int grade;
    private String division;
    private int term;
    private int subjectId;
    private int examType;
    private List<CMarks> cMarksList;
    private List<CResults> cResultsList;



    public int getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(int schoolNo) {
        this.schoolNo = schoolNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getExamType() {
        return examType;
    }

    public void setExamType(int examType) {
        this.examType = examType;
    }

    public List<CMarks> getcMarksList() {
        return cMarksList;
    }

    public void setcMarksList(List<CMarks> cMarksList) {
        this.cMarksList = cMarksList;
    }

    public List<CResults> getcResultsList() {
        return cResultsList;
    }

    public void setcResultsList(List<CResults> cResultsList) {
        this.cResultsList = cResultsList;
    }
}
