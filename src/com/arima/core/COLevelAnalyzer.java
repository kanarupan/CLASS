package com.arima.core;

import com.arima.classifier.CJ48Classifier;

public class COLevelAnalyzer extends CAnalyzer {
	
	public COLevelAnalyzer() {
		
		super();
		classifierType = new CJ48Classifier();
	}
}
