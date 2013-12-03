package com.arima.classengine.classifier;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.Bagging;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 12/3/13
 * Time: 7:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class CBagging implements CClassifier {

    public static void main(String[] args) throws Exception {
        int bins = 4;
        Instances train = Utils.prepareTrainData(11, 3, "MATHEMATICS");
        train = CFilter.removeAttributesByIndices(train, "1");
        train = CFilter.numeric2nominal(train, "first-last",bins);

        //Changing nominal lables so that every attribute will have the same
        train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(bins, true));

        //Renaming attribute values to different lables such as A,B,C,S,F
        train = Utils.renameAttributes(train, bins);
        train.setClassIndex(train.numAttributes()-1);


        Bagging bagging = new Bagging();
        bagging.setClassifier(new J48());
//        bagging.setBagSizePercent(10);

        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(new J48(), train, 10, new Random(1));

        System.out.println(eval.pctCorrect());
    }

    @Override
    public Classifier buildClassifier(Instances subject) throws Exception {


        return null;
    }


}
