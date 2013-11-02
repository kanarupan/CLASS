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
//import weka.classifiers.functions.MultilayerPerceptron;
//import weka.classifiers.trees.J48;
//import weka.core.Instances;
//
//public class CMultiLayerPerceptronClassifier implements CClassifier {
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
//		subject.deleteWithMissingClass();
//
//		MultilayerPerceptron mlp = new MultilayerPerceptron();
//		mlp.buildClassifier(subject);
//		CAnalyzer.selectAttributes(subject);
//
//		Evaluation eval = new Evaluation(subject);
//		eval.crossValidateModel(mlp, subject, 10, new Random(1));
//
//		//selectAttributes(subject);
//
//		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
//		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");
//
//		return mlp;
//	}
//
//}
