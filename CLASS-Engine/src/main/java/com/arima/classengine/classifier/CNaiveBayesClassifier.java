package com.arima.classengine.classifier;

import java.util.Random;
import com.arima.classengine.filter.CFilter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * class for building Naive Bayes classifier
 */
public class CNaiveBayesClassifier implements CClassifier {

    /**
     * build the Naive Bayes classifier on the given train data
     * @param subject the training instances to build the model
     * @return the trained Naive Bayes classifier
     * @throws Exception
     */
	public Classifier buildClassifier(Instances subject)
			throws Exception {

        //set the last attributes as class attribute if not set
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(subject);

		return nb;
	}

}
