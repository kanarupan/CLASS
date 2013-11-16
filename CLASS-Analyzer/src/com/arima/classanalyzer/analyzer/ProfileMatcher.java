package com.arima.classanalyzer.analyzer;

import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.EngineService;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.IEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kana
 * Date: 11/15/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileMatcher {

    //it will return nearest profiles from same school (ySchool DB index numbers)
    public static List<Integer> getNearestLocalProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{
        EngineService engineService = new EngineService();
        IEngine engine = engineService.getEnginePort();
        return engine.getNearestLocalProfiles(schoolNo,grade,term,subjects,marks);
    }

    //it will return nearest profiles from all schools (central DB index numbers)
    public static ArrayList<Integer> getNearestGlobalProfiles(int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{
        EngineService engineService = new EngineService();
        IEngine engine = engineService.getEnginePort();
        return (ArrayList<Integer>) engine.getNearestGlobalProfiles(grade,term,subjects,marks);

    }

}
