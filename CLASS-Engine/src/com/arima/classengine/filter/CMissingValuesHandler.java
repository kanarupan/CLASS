package com.arima.classengine.filter;

import weka.core.Instances;

public interface CMissingValuesHandler {
	Instances handleMissingValues(Instances train) throws Exception;

}
