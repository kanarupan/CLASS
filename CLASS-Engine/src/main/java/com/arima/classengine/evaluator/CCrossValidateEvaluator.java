package com.arima.classengine.evaluator;

import java.util.Random;

import com.arima.classengine.classifier.CJ48Classifier;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class CCrossValidateEvaluator implements CEvaluator {

	public Evaluation evaluator(Classifier cls, Instances inst, int fold, int seed)
			throws Exception {
		
		Evaluation eval = new Evaluation(inst);
		eval.crossValidateModel(cls, inst, fold, new Random(seed));

		
		double accuracy = 100 * (eval.correct())/(eval.correct()+eval.incorrect());
//		System.out.println("Fucking Accuracy is : " + accuracy);
//		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
//		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
//		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");
		return eval;
	}

}
