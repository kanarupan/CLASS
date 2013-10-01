package com.arima.analyzer;

import com.arima.classifier.CNaiveBayesClassifier;

public class COLevelAnalyzer extends CAnalyzer {
	
	public COLevelAnalyzer() {
		
		super();
		classifierType = new CNaiveBayesClassifier();
	}
}
