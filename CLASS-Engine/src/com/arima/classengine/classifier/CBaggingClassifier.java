package com.arima.classengine.classifier;
//package com.arima.classifier;
//
//import java.util.Random;
//
//import com.arima.filter.CFilter;
//
//import weka.classifiers.Classifier;
//import weka.classifiers.Evaluation;
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.functions.LinearRegression;
//import weka.classifiers.functions.MultilayerPerceptron;
//import weka.classifiers.meta.AdaBoostM1;
//import weka.classifiers.meta.Bagging;
//import weka.classifiers.trees.J48;
//import weka.classifiers.trees.M5P;
//import weka.core.Instances;
//
//public class CBaggingClassifier implements CClassifier {
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
//		J48 j48 = new J48();
////		j48.setUnpruned(true);
////		j48.buildClassifier(subject);
////		AdaBoostM1 ada = new AdaBoostM1();
////		ada.setClassifier(new J48());
////		ada.buildClassifier(subject);
//		
////		Bagging bag = new Bagging();
//		
//		AdaBoostM1 bag = new AdaBoostM1();
//		
//		bag.setClassifier( new J48());
////		bag.setSeed(3);
//		bag.buildClassifier(subject);
////		CAnalyzer.selectAttributes(subject);
//
//		Evaluation eval = new Evaluation(subject);
//		eval.crossValidateModel(bag, subject, 10, new Random(1));
//
//		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
//		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");
//
//		return bag;
//		
//	}
//
//}
