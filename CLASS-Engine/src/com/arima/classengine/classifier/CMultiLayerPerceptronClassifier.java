package com.arima.classengine.classifier;

import java.util.Random;

import com.arima.classengine.filter.CFilter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class CMultiLayerPerceptronClassifier implements CClassifier {

	public Classifier buildClassifier(Instances subject)
			throws Exception {
		

		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		MultilayerPerceptron mlp = new MultilayerPerceptron();
		mlp.buildClassifier(subject);

		return mlp;
	}

}
