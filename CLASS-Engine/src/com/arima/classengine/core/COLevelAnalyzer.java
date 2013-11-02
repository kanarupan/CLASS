package com.arima.classengine.core;

import com.arima.classengine.classifier.CJ48Classifier;

public class COLevelAnalyzer extends CAnalyzer {
	
	public COLevelAnalyzer() {
		
		super();
		classifierType = new CJ48Classifier();
	}
}
