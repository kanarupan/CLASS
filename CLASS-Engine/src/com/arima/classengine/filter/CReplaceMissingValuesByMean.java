package com.arima.classengine.filter;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

public class CReplaceMissingValuesByMean implements CMissingValuesHandler {

	public Instances handleMissingValues(Instances train) throws Exception {

			ReplaceMissingValues rmv = new ReplaceMissingValues();
			rmv.setInputFormat(train);
			train = Filter.useFilter(train, rmv);

		return train;
	}

}
