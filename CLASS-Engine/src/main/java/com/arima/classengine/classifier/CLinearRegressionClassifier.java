package com.arima.classengine.classifier;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;

public class CLinearRegressionClassifier implements CClassifier {

	public Classifier buildClassifier(Instances subject) throws Exception {
		
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		LinearRegression lr = new LinearRegression();
		lr.buildClassifier(subject);
		
		return lr;
	}

}
