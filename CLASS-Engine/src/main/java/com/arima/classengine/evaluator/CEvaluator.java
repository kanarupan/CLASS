package com.arima.classengine.evaluator;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 * interface for classes which are evaluating classifiers
 */
public interface CEvaluator {

    /**
     * abstract method to evaluate a classifier
     * @param cls the classifier to be evaluated
     * @param inst the training data
     * @param fold the number of fold
     * @param seed the number of seeds
     * @return
     * @throws Exception
     */
	Evaluation evaluator(Classifier cls, Instances inst, int fold, int seed) throws Exception;
}
