package com.arima.classengine.core;

import com.arima.classengine.classifier.*;
import com.arima.classengine.evaluator.CCrossValidateEvaluator;
import com.arima.classengine.evaluator.CEvaluator;
import com.arima.classengine.filter.CFilter;
import com.arima.classengine.filter.CMissingValuesHandler;
import com.arima.classengine.utils.Utils;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.instance.Resample;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 12/1/13
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    private CClassifier classifierType;
    private CEvaluator evaluatorType;
    private  int binSize = 5;
    private Classifier model;
    private  Instances train = null;
    private Evaluation eval = null;
    private double accuracy = 0;
    private final double accuracyThreshold = 75;
    private CMissingValuesHandler missingValueHandlerType;
    private final boolean isTest = false;
    private Classifier tempModel;
    private double timeToBuild = 0;

    /**
     * @return the classifierType
     */
    private CClassifier getClassifierType() {
        return classifierType;
    }

    /**
     * @param classifierType the classifierType to set
     */
    private void setClassifierType(CClassifier classifierType) {
        this.classifierType = classifierType;
    }

    /**
     * @return the evaluatorType
     */
    private CEvaluator getEvaluatorType() {
        return evaluatorType;
    }

    /**
     * @param evaluatorType the evaluatorType to set
     */
    private void setEvaluatorType(CEvaluator evaluatorType) {
        this.evaluatorType = evaluatorType;
    }

    /**
     * @return the binSize
     */
    private int getBinSize() {
        return binSize;
    }

    /**
     * @param binSize the binSize to set
     */
    private void setBinSize(int binSize) {
        this.binSize = binSize;
    }

    /**
     * @return the model
     */
    private Classifier getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    private void setModel(Classifier model) {
        this.model = model;
    }

    /**
     * @return the train
     */
    private Instances getTrain() {
        return train;
    }

    /**
     * @param train the train to set
     */
    private void setTrain(Instances train) {
        this.train = train;
    }

    /**
     * @return the eval
     */
    private Evaluation getEval() {
        return eval;
    }

    /**
     * @param eval the eval to set
     */
    private void setEval(Evaluation eval) {
        this.eval = eval;
    }



    /**
     * @return the missingValueHandlerType
     */
    private CMissingValuesHandler getMissingValueHandlerType() {
        return missingValueHandlerType;
    }

    /**
     * @param missingValueHandlerType the missingValueHandlerType to set
     */
    private void setMissingValueHandlerType(
            CMissingValuesHandler missingValueHandlerType) {
        this.missingValueHandlerType = missingValueHandlerType;
    }


    public static void main(String[] args) throws Exception {
        int bins = 3;
        Instances train = Utils.prepareTrainData(11, 3, "MATHEMATICS");
        train = CFilter.removeAttributesByIndices(train, "1");
        train = CFilter.numeric2nominal(train, "first-last",bins);

        //Changing nominal lables so that every attribute will have the same
        train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(bins, true));

        //Renaming attribute values to different lables such as A,B,C,S,F
        train = Utils.renameAttributes(train, bins);

        for (int i = 0; i < train.numAttributes(); i++) {
            train.deleteWithMissing(i);
        }

        train.setClassIndex(train.numAttributes()-1);


        Test test = new Test();
//        test.setClassifierType(new CMultiLayerPerceptronClassifier());
//        test.setClassifierType(new CJ48Classifier());
        test.setClassifierType(new CNaiveBayesClassifier());
//        test.setClassifierType(new CBoostJ48());
        test.setEvaluatorType(new CCrossValidateEvaluator());


        Instances testSet= new Instances(train);
        for (int i=testSet.numInstances()-1; i>=100; i--){
            testSet.delete(testSet.numInstances()-1);
        }

        Resample rs = new Resample();
        rs.setInputFormat(testSet);
//        int[] a={1};
//        rs.createSubsampleWithoutReplacement(new Random(1), train.numInstances(), 100, 1,a);
        rs.setSampleSizePercent(100);

//        Instances train1 = Filter.useFilter(train, rs);
        testSet = Filter.useFilter(testSet, rs);


       Instances trainSet = new Instances(train);
        for (int i=0; i<100; i++){
            trainSet.delete(0);
        }

        Resample rs1 = new Resample();
        rs1.setInputFormat(trainSet);
//        int[] a={1};
//        rs.createSubsampleWithoutReplacement(new Random(1), train.numInstances(), 100, 1,a);
        rs1.setSampleSizePercent(1000);

//        Instances train1 = Filter.useFilter(train, rs);
        trainSet = Filter.useFilter(trainSet, rs1);

//        System.out.println(testSet.numInstances());
//        System.out.println(trainSet.numInstances());
//        System.exit(0);


        System.out.println(trainSet.numInstances());
        System.out.println(testSet.numInstances());
//               Classifier cls = new J48();

        Classifier cls = test.getClassifierType().buildClassifier(trainSet);
//        System.out.println(cls);
//        test.setEval(test.getEvaluatorType().evaluator(cls, train, 10, 1));
        Evaluation eval = new Evaluation(trainSet);
        eval.evaluateModel(cls, testSet);

//        System.out.println(test.getEval().pctCorrect());
        System.out.println(eval.pctCorrect());

//        System.out.println(train);


    }
}
