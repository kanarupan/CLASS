package com.arima.classanalyzer.core;

import com.arima.classanalyzer.classifier.CJ48Classifier;

public class COLevelAnalyzer extends CAnalyzer {
	
	public COLevelAnalyzer() {
		
		super();
		classifierType = new CJ48Classifier();
	}
}
