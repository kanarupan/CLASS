package com.arima.classifier;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import com.arima.analyzer.CAnalyzer;
import com.arima.filter.CFilter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;


public class CJ48Classifier implements CClassifier {

	@Override
	public Classifier  buildClassifier(Instances subject, String indices) throws Exception {

		subject = CFilter.removeAttributesByIndices(subject, indices);

		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
		j48.setUnpruned(true);
		j48.buildClassifier(subject);
//		CAnalyzer.selectAttributes(subject);

		Evaluation eval = new Evaluation(subject);
		eval.crossValidateModel(j48, subject, 10, new Random(1));

		System.out.println(eval.toSummaryString("\nResults\nn8888", true));
		System.out.println(eval.fMeasure(1) + " " + eval.precision(1) + " ");

		return j48;

	}

}
