package com.arima.classanalyzer.analyzer;

import com.arima.classanalyzer.core.ExamStandard;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.EngineService;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.IEngine;

/**
 * Created with IntelliJ IDEA.
 * User: kana
 * Date: 11/15/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExamComparator {


    //it will return nearest
    public static ExamStandard getExamStandard(int schoolNo, int year, int grade, String subject) throws Exception {
        EngineService engineService = new EngineService();
        IEngine engine = engineService.getEnginePort();
        return engine.getStudentBasedStandard(schoolNo, year, grade, subject);
    }

}
