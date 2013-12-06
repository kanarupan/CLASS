package com.arima.classengine.evaluator;

import java.util.Random;

import com.arima.classengine.classifier.CJ48Classifier;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 * class for evaluating classifiers
 */
public class CCrossValidateEvaluator implements CEvaluator {

    /**
     * evaluates a classifier
     * @param cls the classifier to be evaluated
     * @param inst the training data
     * @param fold the number of fold
     * @param seed the number of seeds
     * @return the encapsulated evaluation stats
     * @throws Exception
     */
	public Evaluation evaluator(Classifier cls, Instances inst, int fold, int seed)
			throws Exception {

        //performing k fold cross validation
		Evaluation eval = new Evaluation(inst);
		eval.crossValidateModel(cls, inst, fold, new Random(seed));

		return eval;
	}

}
