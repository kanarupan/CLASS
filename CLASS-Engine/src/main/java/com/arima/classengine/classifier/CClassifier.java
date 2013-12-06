package com.arima.classengine.classifier;
import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * Interface to classes which are building classifiers
 */
public interface CClassifier {

    /**
     * Abstract method for building a classifier
     * @param subject the training instances to build the model
     * @return the trained classifier
     * @throws Exception
     */
	Classifier buildClassifier(Instances subject) throws Exception;

}
