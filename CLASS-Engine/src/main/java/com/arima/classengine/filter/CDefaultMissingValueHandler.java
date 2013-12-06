package com.arima.classengine.filter;

import weka.core.Instances;

/**
 * class for letting the classification algorithm to handle the missing values
 */
public class CDefaultMissingValueHandler implements CMissingValuesHandler {

    /**
     * letting the classifier to handle the missing values
     * @param train the data set to be validated/handled for missing values
     * @return the modified data set which are not having missing values
     * @throws Exception
     */
	public Instances handleMissingValues(Instances train) throws Exception {
		return train;
	}

}
