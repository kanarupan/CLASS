package com.arima.evaluator;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public interface CEvaluator {
	Evaluation evaluator(Classifier cls, Instances inst, int fold, int seed) throws Exception;
}
