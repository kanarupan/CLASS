package com.arima.classengine.filter;

import weka.core.Instances;

/**
 * interface for classes which are handling missing values
 */
public interface CMissingValuesHandler {
    /**
     * handling the missing values
     * @param train the data set to be validated/handled for missing values
     * @return the modified data set which are not having any missing values
     * @throws Exception
     */
    Instances handleMissingValues(Instances train) throws Exception;

}
