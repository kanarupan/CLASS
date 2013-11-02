package com.arima.classengine.classifier;
//package com.arima.classifier;
//import java.util.Random;
//
//import com.arima.analyzer.CAnalyzer;
//import com.arima.filter.CFilter;
//
//import weka.classifiers.Classifier;
//import weka.classifiers.Evaluation;
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.trees.J48;
//import weka.core.Instances;
//
//
//public class CNaiveBayesClassifier implements CClassifier {
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
//		NaiveBayes nb = new NaiveBayes();
//		nb.buildClassifier(subject);
//		CAnalyzer.selectAttributes(subject);
//		
//		Evaluation eval = new Evaluation(subject);
//		eval.crossValidateModel(nb, subject, 10, new Random(1));
//
//		//selectAttributes(subject);
//
//		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
//		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");
//
//		return nb;
//	}
//
//}
