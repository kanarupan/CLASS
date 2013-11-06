package com.arima.classengine.classifier;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Bagging;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.M5P;
import weka.core.Instances;

public class CBaggingClassifier implements CClassifier {

	public Classifier buildClassifier(Instances subject) throws Exception {
		
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
//		j48.setUnpruned(true);
//		j48.buildClassifier(subject);
//		AdaBoostM1 ada = new AdaBoostM1();
//		ada.setClassifier(new J48());
//		ada.buildClassifier(subject);
		
//		Bagging bag = new Bagging();
		
		AdaBoostM1 bag = new AdaBoostM1();
		
		bag.setClassifier( new J48());
//		bag.setSeed(3);
		bag.buildClassifier(subject);

		return bag;
		
	}

}
