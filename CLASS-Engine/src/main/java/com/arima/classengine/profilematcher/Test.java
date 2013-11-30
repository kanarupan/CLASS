package com.arima.classengine.profilematcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 11/29/13
 * Time: 12:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<String> subjects = new ArrayList<String>();
        subjects.add("SAIVISM");
        subjects.add("MATHEMATICS");
        subjects.add("SCIENCE AND TECHNOLOGY");
        subjects.add("TAMIL LANGUAGE");
        subjects.add("ENGLISH LANGUAGE");
        subjects.add("HISTORY");
//		subjects.add("INFORMATION AND COMMUNICATION TECHNOLOGY");
//		subjects.add("BUSSINESS AND ACCOUNTING");

        ArrayList<Integer> marks = NearestProfileKDTree.getMarks();

        NearestProfileKDTree.getNearestProfiles(11, 3, subjects, marks);
        NearestProfileBallTree.getNearestProfiles(11, 3, subjects, marks);
        NearestProfileCoverTree.getNearestProfiles(11,3,subjects,marks);


    }
}
