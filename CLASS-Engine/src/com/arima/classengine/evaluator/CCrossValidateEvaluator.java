package com.arima.classengine.evaluator;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class CCrossValidateEvaluator implements CEvaluator {

	public Evaluation evaluator(Classifier cls, Instances inst, int fold, int seed)
			throws Exception {
		
		Evaluation eval = new Evaluation(inst);
		eval.crossValidateModel(cls, inst, fold, new Random(seed));

//		System.out.print("Correctly Classified Instances : "); System.out.println(eval.correct());
//		System.out.print("Incorrectly Classified Instances : ");	System.out.println(eval.incorrect());
		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");
		return eval;
	}

}
