package com.arima.classanalyzer.core;

import com.arima.classanalyzer.classifier.CJ48Classifier;

public class CALevelAnalyzer extends CAnalyzer {

	public CALevelAnalyzer() {
		super();
		classifierType = new CJ48Classifier();
	}

}
