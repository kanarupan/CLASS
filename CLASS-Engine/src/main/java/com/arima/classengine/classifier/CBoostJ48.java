package com.arima.classengine.classifier;

import java.util.Random;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.Vote;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.M5P;
import weka.core.Instances;

/**
 * class for boosting C4.5
 */
public class CBoostJ48 implements CClassifier {

    public static void main(String[] args) throws Exception {
        int bins = 5;
        Instances train = Utils.prepareTrainData(11, 3, "MATHEMATICS");
        train = CFilter.removeAttributesByIndices(train, "1");
        train = CFilter.numeric2nominal(train, "first-last",bins);

        //Changing nominal lables so that every attribute will have the same
        train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(bins, true));

        //Renaming attribute values to different lables such as A,B,C,S,F
        train = Utils.renameAttributes(train, bins);
        train.setClassIndex(train.numAttributes()-1);

//        J48 j48 = new J48();
//        j48.setUnpruned(true);
//        j48.buildClassifier(train);
//
//        NaiveBayes nb = new NaiveBayes();
//        nb.buildClassifier(train);
//
//        MultilayerPerceptron mlp = new MultilayerPerceptron();
//        mlp.buildClassifier(train);
//
//        Vote vote = new Vote();
//        vote.setClassifiers(new Classifier[]{j48, nb, mlp});
//        vote.buildClassifier(train);

        AdaBoostM1 boost = new AdaBoostM1();
//        boost.setClassifier(vote);
//        boost.setUseResampling(false);
//        boost.setNumIterations(10);

//        boost.buildClassifier(train);
        System.out.println(boost.getNumIterations());

        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(boost, train, 10, new Random(1));

        System.out.println(eval.pctCorrect());
    }

    /**
     * build boosted C4.5 classifier
     * @param subject the training instances to build the model
     * @return the trained boosted C4.5 classifier
     * @throws Exception
     */
	public Classifier buildClassifier(Instances subject) throws Exception {
		
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
		
		AdaBoostM1 boost = new AdaBoostM1();

        boost.setClassifier( new J48());
//		bag.setSeed(3);
        boost.buildClassifier(subject);

		return boost;
		
	}

}
