package com.arima.classengine.core;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import com.arima.classengine.classifier.CClassifier;
import com.arima.classengine.classifier.CJ48Classifier;
import com.arima.classengine.evaluator.CCrossValidateEvaluator;
import com.arima.classengine.evaluator.CEvaluator;
import com.arima.classengine.filter.CFilter;

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

	private static int binSize = 5;

	public static void setBinSize(int bins){
		binSize = bins;
	}

	public static int getBinSize(){
		return binSize;
	}
	
	private static Classifier model;

	public static void setClassifier(Classifier cls){
		model = cls;
	}

	public static Classifier getClassifier(){
		return model;
	}
	
	private static Instances train = null;
	
	public static void setTrain(Instances data){
		train = data;
	}
	public static Instances getTrain(){
		return train;
	}
	
	

	public static Classifier getModel(Instances train) throws Exception{
		
//		double accuracy;
		
		CAnalyzer.setTrain(train);
		
		for (int bins = 5; bins > 1; bins--) {
			
			train = CAnalyzer.getTrain();
			train = CFilter.removeAttributesByNames(train, "index_no");

			train = CFilter.numeric2nominal(train, "first-last",bins);
			//		train = CFinal.handleMissingValues(train);

			//Changing nominal lables so that every attribute will have the same
			train = CFinal.changeAttributeNominalRange(train, CFinal.getAttributeLables(bins, true));

			//Renaming attribute values to different lables such as A,B,C,S,F
			train = CFinal.renameAttributes(train, bins);

			/*
			 * strategy design pattern to dynamically change classifier algorithms from use case to use case
			 * ALevelAnalyzer will run J48
			 * OLevelAnalyzwe will run NaiveBayes
			 */
			
			CAnalyzer analyzer = new CALevelAnalyzer();
			analyzer.setClassifierType(new CJ48Classifier());
			analyzer.setEvaluatorType(new CCrossValidateEvaluator());

			model = (J48) analyzer.classifierType.buildClassifier(train);
			Evaluation eval = analyzer.evaluatorType.evaluator(model, train, 10, 1);
			
			System.out.println(analyzer.classifierType.getClass());
			
//			System.out.println(eval.toSummaryString());
			CFilter.appendfile("C:/JSF/stat.txt", eval.toSummaryString());
			CAnalyzer.setBinSize(bins);
			CAnalyzer.setClassifier(model);
//			J48 ne = (J48)model;
//			System.out.println("Rules" + ne.toSource(ne.toString()));
//			System.exit(0);
			
		}
		
		return CAnalyzer.getClassifier();
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
