package com.arima.classengine.classifier;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class CVoteClassifier implements CClassifier {

	public Classifier buildClassifier(Instances subject) throws Exception {
		
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
		j48.setUnpruned(true);
		j48.buildClassifier(subject);

		return j48;
	}

}
