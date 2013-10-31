package com.arima.core;

import com.arima.classifier.CJ48Classifier;

public class CALevelAnalyzer extends CAnalyzer {

	public CALevelAnalyzer() {
		super();
		classifierType = new CJ48Classifier();
	}

}
