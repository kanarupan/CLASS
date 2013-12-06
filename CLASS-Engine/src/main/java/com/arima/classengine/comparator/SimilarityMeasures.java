package com.arima.classengine.comparator;

import com.arima.classengine.engine.ExamStandard;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 12/6/13
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimilarityMeasures {

    public static void main(String[] args) throws Exception {
//        getHammingSimilarity(11086, 2009, 11, "SCIENCE AND TECHNOLOGY");
        ExamStandard examStandard = getExamStandard(11086, 2009, 11, "SCIENCE AND TECHNOLOGY");
        System.out.println(examStandard.getGeneral());
        System.out.println(examStandard.getGeneralCount());
        System.out.println(examStandard.getSequenceAlignmentScore());
        System.out.println(examStandard.getJaccardIndex());
        System.out.println(examStandard.getTerm());
        System.out.println(examStandard.getTermCount());
    }

    public static ExamStandard getExamStandard(int schoolNo, int year, int grade, String subject) throws Exception {

        ExamStandard examStandard = new ExamStandard();

        examStandard = JaccardIndex.getJaccardIndexSimilarity(schoolNo, year, grade, subject, examStandard);

        examStandard = HammingDistance.getHammingSimilarity(schoolNo, year, grade, subject, examStandard);

        return examStandard;
    }
}
