package com.arima.classengine.filter;

import weka.core.Instances;

public class CDefaultMissingValueHandler implements CMissingValuesHandler {

	public Instances handleMissingValues(Instances train) throws Exception {
		return train;
	}

}
