package com.arima.classanalyzer.core;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import com.arima.classanalyzer.classifier.CClassifier;
import com.arima.classanalyzer.classifier.CJ48Classifier;
import com.arima.classanalyzer.evaluator.CEvaluator;
import com.arima.classanalyzer.filter.CFilter;

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
	
	
	public static Instances predict(Instances test, Classifier model, int bins ) throws Exception{


		Instances toPredict = new Instances(test);
		toPredict = CFilter.removeAttributesByNames(toPredict, "index_no");
		toPredict = CFilter.addAttributes(toPredict, "final", "nominal", "last", CFinal.getAttributeLables(bins, true));
		toPredict = CFilter.numeric2nominal(toPredict, "first-last",bins);

		toPredict = CFinal.changeAttributeNominalRange(toPredict, CFinal.getAttributeLables(bins, true));

		toPredict = CFinal.renameAttributes(toPredict, bins);
		
		

		Instances subject_test = new Instances(toPredict);
		subject_test.setClassIndex(subject_test.numAttributes() - 1);
		
		

		test = CFilter.removeAttributesByIndices(test, "2-last");			//now "test" will contain only the index numbers
		test = CFilter.addAttributes(test, "final","nominal", "last", CFinal.getAttributeLables(bins, false));

//		System.out.println(test);System.exit(0);
		
		Instances predicted = new Instances(test);		//"predicted" will hold the index numbers with their  final results=null (predicted)

		predicted.setClassIndex(predicted.numAttributes()-1);
		
		return CAnalyzer.predict(subject_test, model, predicted);

	}



	public static Instances predict(Instances toPredict, Classifier classifier, Instances predicted) throws Exception {

		for (int i = 0; i < toPredict.numInstances(); i++) {

			double pred = classifier.classifyInstance(toPredict.instance(i));
			System.out.print("actual: " + toPredict.classAttribute().value((int) toPredict.instance(i).classValue()));
			System.out.println(", predicted: " + toPredict.classAttribute().value((int) pred));
			toPredict.instance(i).setClassValue(pred);
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
