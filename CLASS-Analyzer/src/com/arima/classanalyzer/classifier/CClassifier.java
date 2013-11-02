package com.arima.classanalyzer.classifier;
import weka.classifiers.Classifier;
import weka.core.Instances;


public interface CClassifier {
	
	Classifier buildClassifier(Instances subject) throws Exception;

}
