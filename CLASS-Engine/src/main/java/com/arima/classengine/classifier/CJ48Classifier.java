package com.arima.classengine.classifier;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import com.arima.classengine.core.CAnalyzer;
import com.arima.classengine.filter.CFilter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

/**
 * class for building a C4.5 classifier
 */
public class CJ48Classifier implements CClassifier {

    /**
     * build the C4.5 classifier on the given train data
     * @param subject the training instances to build the model
     * @return the trained C4.5 classifier
     * @throws Exception
     */
	public Classifier  buildClassifier(Instances subject) throws Exception {

        //set the last attributes as class attribute if not set
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
		j48.setUnpruned(true);
		j48.buildClassifier(subject);

		return j48;

	}

}
