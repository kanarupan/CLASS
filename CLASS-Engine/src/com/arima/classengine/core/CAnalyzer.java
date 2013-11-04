package com.arima.classengine.core;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.arima.classengine.classifier.CClassifier;
import com.arima.classengine.classifier.CJ48Classifier;
import com.arima.classengine.classifier.CMultiLayerPerceptronClassifier;
import com.arima.classengine.evaluator.CCrossValidateEvaluator;
import com.arima.classengine.evaluator.CEvaluator;
import com.arima.classengine.filter.CDefaultMissingValueHandler;
import com.arima.classengine.filter.CDiscardInstancesWithMissingValues;
import com.arima.classengine.filter.CEngineFilter;
import com.arima.classengine.filter.CFilter;
import com.arima.classengine.filter.CMissingValuesHandler;
import com.arima.classengine.filter.CReplaceMissingValuesByMean;
import com.arima.classengine.classifier.CNaiveBayesClassifier;

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



	private CClassifier classifierType;
	private CEvaluator evaluatorType;
	private  int binSize = 5;
	private  Classifier model;
	private  Instances train = null;
	private Evaluation eval = null;	
	private double accuracy = 0;
	private final double accuracyThreshold = 85;
	private CMissingValuesHandler missingValueHandlerType; 
	private final boolean isTest = true;

	/**
	 * @return the classifierType
	 */
	private CClassifier getClassifierType() {
		return classifierType;
	}

	/**
	 * @param classifierType the classifierType to set
	 */
	private void setClassifierType(CClassifier classifierType) {
		this.classifierType = classifierType;
	}

	/**
	 * @return the evaluatorType
	 */
	private CEvaluator getEvaluatorType() {
		return evaluatorType;
	}

	/**
	 * @param evaluatorType the evaluatorType to set
	 */
	private void setEvaluatorType(CEvaluator evaluatorType) {
		this.evaluatorType = evaluatorType;
	}

	/**
	 * @return the binSize
	 */
	private int getBinSize() {
		return binSize;
	}

	/**
	 * @param binSize the binSize to set
	 */
	private void setBinSize(int binSize) {
		this.binSize = binSize;
	}

	/**
	 * @return the model
	 */
	private Classifier getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	private void setModel(Classifier model) {
		this.model = model;
	}

	/**
	 * @return the train
	 */
	private Instances getTrain() {
		return train;
	}

	/**
	 * @param train the train to set
	 */
	private void setTrain(Instances train) {
		this.train = train;
	}

	/**
	 * @return the eval
	 */
	private Evaluation getEval() {
		return eval;
	}

	/**
	 * @param eval the eval to set
	 */
	private void setEval(Evaluation eval) {
		this.eval = eval;
	}


	/**
	 * @return the accuracy
	 */
	private double getAccuracy() {
		return accuracy;
	}

	/**
	 * @param accuracy the accuracy to set
	 */
	private void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * @return the accuracyThreshold
	 */
	private double getAccuracyThreshold() {
		return accuracyThreshold;
	}


	/**
	 * @return the missingValueHandlerType
	 */
	private CMissingValuesHandler getMissingValueHandlerType() {
		return missingValueHandlerType;
	}

	/**
	 * @param missingValueHandlerType the missingValueHandlerType to set
	 */
	private void setMissingValueHandlerType(
			CMissingValuesHandler missingValueHandlerType) {
		this.missingValueHandlerType = missingValueHandlerType;
	}

	/**
	 * @return the isTest
	 */
	private boolean isTest() {
		return isTest;
	}

	public static void main(String[] args) throws Exception {

		Instances train = CFilter.retrieveDatasetFromDatabase("select * from ol_model", "root", "");

		//		System.out.println(train);
		CAnalyzer analyzer = getModel(train);
		System.out.println(analyzer.getAccuracy());
		//getModel(train) will return a best model among other models to this train set
		//		Classifier model = CAnalyzer.getModel(train);

	}	

	public static CAnalyzer getModel(Instances train) throws Exception{

		String header;
		CAnalyzer analyzer = new CAnalyzer();
		analyzer.setTrain(train);

		List<CClassifier> classifiers = new ArrayList<CClassifier>();
		classifiers.add(new CJ48Classifier());
		classifiers.add(new CNaiveBayesClassifier());
		classifiers.add(new CMultiLayerPerceptronClassifier());

		List<CMissingValuesHandler> missingValueHandlers = new ArrayList<CMissingValuesHandler>();
		missingValueHandlers.add(new CDefaultMissingValueHandler());
		missingValueHandlers.add(new CReplaceMissingValuesByMean());
		missingValueHandlers.add(new CDiscardInstancesWithMissingValues());

		binsloop:
			for (int bins = 5; bins >= 2; bins--) {

				for(int i=1; i<=classifiers.size(); i++){

					for(int x=1; x<=missingValueHandlers.size(); x++){

						train = analyzer.getTrain();
						train = CFilter.removeAttributesByNames(train, "index_no");

						//handling missing values using different methods
						analyzer.setMissingValueHandlerType(missingValueHandlers.get(x-1));
						train = analyzer.missingValueHandlerType.handleMissingValues(train);

						train = CFilter.numeric2nominal(train, "first-last",bins);
						//			train = CEngineFilter.handleMissingValues(train);

						//Changing nominal lables so that every attribute will have the same
						train = CEngineFilter.changeAttributeNominalRange(train, CEngineFilter.getAttributeLables(bins, true));

						//Renaming attribute values to different lables such as A,B,C,S,F
						train = CEngineFilter.renameAttributes(train, bins);

						analyzer.setClassifierType(classifiers.get(i-1));
						analyzer.setEvaluatorType(new CCrossValidateEvaluator());

						analyzer.setModel(analyzer.classifierType.buildClassifier(train));
						analyzer.setEval(analyzer.evaluatorType.evaluator(analyzer.model, train, 10, 1));

						if(analyzer.isTest() || ( (!analyzer.isTest()) && (analyzer.getAccuracy() > analyzer.getEval().pctCorrect()) )){
							analyzer.setBinSize(bins);
							analyzer.setModel(analyzer.model);
							analyzer.setAccuracy(analyzer.getEval().pctCorrect());
						}


						if(analyzer.isTest()){
							header = "Classifier : " + analyzer.getModel().getClass().getName() 
									+
									"\n\n"
									+
									"Number of bins : " + analyzer.getBinSize()
									+
									"\n\n"
									+
									"Handling missing values" + analyzer.getMissingValueHandlerType().getClass().getName()
									+
									"\n" ;

							System.out.println(header);
							CFilter.appendfile("C:/JSF/stat.txt", header);
							CFilter.appendfile("C:/JSF/stat.txt", analyzer.getEval().toSummaryString());
							CFilter.appendfile("C:/JSF/stat.txt", "###################################################################" + "\n");
						
						}
						//			J48 ne = (J48)model;
						//			System.out.println("Rules" + ne.toSource(ne.toString()));
						//			System.exit(0);

					}
				}

				if(!analyzer.isTest() && analyzer.getAccuracy() >= analyzer.getAccuracyThreshold())
						break binsloop;	
			}

		return analyzer;

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
