package com.arima.classengine.classifier;

import java.util.Random;

import com.arima.classengine.filter.CFilter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * class for building MLP classifier
 */
public class CMultiLayerPerceptronClassifier implements CClassifier {
    /**
     * build the MLP classifier on the given train data
     * @param subject the training instances to build the model
     * @return the trained MLP classifier
     * @throws Exception
     */
	public Classifier buildClassifier(Instances subject)
			throws Exception {

        //set the last attributes as class attribute if not set
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		MultilayerPerceptron mlp = new MultilayerPerceptron();
		mlp.buildClassifier(subject);

		return mlp;
	}

}
