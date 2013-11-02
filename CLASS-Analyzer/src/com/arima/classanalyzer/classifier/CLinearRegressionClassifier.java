package com.arima.classanalyzer.classifier;
//package com.arima.classifier;
//
//import java.util.Random;
//
//import com.arima.analyzer.CAnalyzer;
//import com.arima.filter.CFilter;
//
//import weka.classifiers.Classifier;
//import weka.classifiers.Evaluation;
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.functions.LinearRegression;
//import weka.core.Instances;
//
//public class CLinearRegressionClassifier implements CClassifier {
//
//	@Override
//	public Classifier buildClassifier(Instances subject, String indices)
//			throws Exception {
//		
//		subject = CFilter.removeAttributesByIndices(subject, indices);
//
//		if (subject.classIndex() == -1)
//			subject.setClassIndex(subject.numAttributes() - 1);
//
//		LinearRegression lr = new LinearRegression();
//		lr.buildClassifier(subject);
//		CAnalyzer.selectAttributes(subject);
//		
//		Evaluation eval = new Evaluation(subject);
//		eval.crossValidateModel(lr, subject, 10, new Random(1));
//
//		//selectAttributes(subject);
//
//		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
//		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");
//
//		return lr;
//	}
//
//}
