package com.arima.analyzer;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import com.arima.classifier.CClassifier;
import com.arima.evaluator.CEvaluator;
import com.arima.filter.CFilter;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.Utils;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class CAnalyzer {
	
	public CClassifier classifierType;
	public CEvaluator evaluatorType;
	
	public void setClassifierType(CClassifier classifierType) {
		this.classifierType = classifierType;
	}
	
	public void setEvaluatorType(CEvaluator evaluatorType) {
		this.evaluatorType = evaluatorType;
	}

	public static Instances predict(Instances toPredict, Classifier classifier, String subject, String subject_indices, Instances predicted) throws Exception {

		String five = "1,2,3,4,5";
		String fives = "F,S,C,B,A";
		String four = "1,2,3,4";
		String fours = "F,S,C,A";
		String three = "1,2,3";
		String threes = "F,S,A";
		String two = "1,2";
		String twos = "F,A";
		
		toPredict = CFilter.addAttributes(toPredict, subject, "nominal", "last", five);
		toPredict = CFilter.numeric2nominal(toPredict, "2-last");
		toPredict = CFilter.changeAttributesNominalValues(toPredict, 
				"2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19", five);
		toPredict = CFilter.renameAttributesValues(toPredict, 
				"2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19", five, fives);

		Instances subject_test = new Instances(toPredict);
		subject_test = CFilter.removeAttributesByIndices(subject_test, subject_indices);
		subject_test.setClassIndex(subject_test.numAttributes() - 1);
		System.out.print(subject_test);

		for (int i = 0; i < subject_test.numInstances(); i++) {
			
			double pred = classifier.classifyInstance(subject_test.instance(i));
			System.out.print("actual: " + subject_test.classAttribute().value((int) subject_test.instance(i).classValue()));
			System.out.println(", predicted: " + subject_test.classAttribute().value((int) pred));
			subject_test.instance(i).setClassValue(pred);
			predicted.instance(i).setClassValue(pred);
		}

		return predicted;
	}


	public static void selectAttributes(Instances data) throws Exception {

		// setup attribute selection
		AttributeSelection attsel = new AttributeSelection();
		// CfsSubsetEval eval = new CfsSubsetEval();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();
		Ranker search = new Ranker();
		search.setNumToSelect(5);
		search.setGenerateRanking(true);
//		System.out.println(search.generateRankingTipText());
		
		attsel.setEvaluator(eval);
		attsel.setSearch(search);
		attsel.SelectAttributes(data);
		attsel.setRanking(true);
		System.out.println(Utils.arrayToString(attsel.rankedAttributes()));
		System.out.println(attsel.toResultsString());

		int[] indices = attsel.selectedAttributes();
		System.out.println("selected attribute indices (starting with 0):\n" + Utils.arrayToString(indices));
	}
	
	public void drawTree(J48 j48) {
		
		TreeVisualizer tv = null;
		try {
			tv = new TreeVisualizer(null, j48.graph(), new PlaceNode2());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame jf = new JFrame("Weka Classifier Tree Visualizer: J48");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(800, 600);
		jf.getContentPane().setLayout(new BorderLayout());
		jf.getContentPane().add(tv, BorderLayout.CENTER);
		jf.setVisible(true);
		// adjust tree
		tv.fitToScreen();
	}

}
