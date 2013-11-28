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
    public static void main(String[] args) {
        List<String> subjects = new ArrayList<String>();
        subjects.add("SAIVISM");
        subjects.add("MATHEMATICS");
        subjects.add("SCIENCE AND TECHNOLOGY");
        subjects.add("TAMIL LANGUAGE");
        subjects.add("ENGLISH LANGUAGE");
        subjects.add("HISTORY");
//		subjects.add("INFORMATION AND COMMUNICATION TECHNOLOGY");
//		subjects.add("BUSSINESS AND ACCOUNTING");

        ArrayList<Integer> marks = new ArrayList<Integer>();
//		marks.add(40);
//		marks.add(30);
        marks.add(65);
        marks.add(80);
        marks.add(96);
        marks.add(46);
        marks.add(67);
        marks.add(86);

        System.out.println(NearestProfileBallTree.getNearestProfiles(11086, 11, 3, subjects, marks));
        System.out.println(NearestProfile.getNearestProfiles(11086, 11, 3, subjects, marks));

    }
}
