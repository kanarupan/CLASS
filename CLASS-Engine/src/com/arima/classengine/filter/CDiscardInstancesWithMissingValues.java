package com.arima.classengine.filter;

import weka.core.Instances;

public class CDiscardInstancesWithMissingValues implements
		CMissingValuesHandler {

	public Instances handleMissingValues(Instances train) throws Exception {
		
		for (int i = 0; i < train.numAttributes(); i++) {
			train.deleteWithMissing(i);
		}
		return train;
	}

}
