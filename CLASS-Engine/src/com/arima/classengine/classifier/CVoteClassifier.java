package com.arima.classengine.classifier;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.Vote;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SelectedTag;

public class CVoteClassifier implements CClassifier {

	public Classifier buildClassifier(Instances subject) throws Exception {
		
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
		j48.setUnpruned(true);
		j48.buildClassifier(subject);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(subject);
		
		Vote vote = new Vote();
		vote.setClassifiers(new Classifier[]{j48, nb});
		
		return vote;
	}

}
