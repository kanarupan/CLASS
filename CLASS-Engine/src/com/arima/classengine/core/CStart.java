package com.arima.classengine.core;
//package com.arima.analyzer;
//import com.arima.classifier.CJ48Classifier;
//import com.arima.classifier.CMultiLayerPerceptronClassifier;
//import com.arima.classifier.CNaiveBayesClassifier;
//import com.arima.filter.CFilter;
//
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.functions.MultilayerPerceptron;
//import weka.classifiers.trees.J48;
//import weka.core.Instances;
//
//public class CStart {
//
//	public static void main(String[] args) throws Exception {
//	
//		//----------------------------------- J48--------------------------
//
//		long startTime = System.nanoTime();
//		Instances train = CFilter.retrieveDatasetFromDatabase("select * from some", "root", "");
//
//		/*
//		 * There are two methods to remove the unnecessary attributes from the data set
//		 * train = CFilter.removeAttributesByIndices(train, "1");
//		 * 			OR
//		 * train = CFilter.removeAttributesByNames(train, "index_no");
//		 */				
//		train = CFilter.removeAttributesByNames(train, "index_no");
//
//		/*
//		 * convert numerical values to nominal values
//		 */
//		train = CFilter.numeric2nominal(train, "first-last");
//		train = CFilter.changeAttributesNominalValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", "1,2,3,4,5");
//		train = CFilter.renameAttributesValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", "1,2,3,4,5", "F,S,C,B,A");
////		System.out.println("---------------------------");
////		System.out.println("Train Data!");
////		System.out.println(train);
////		System.out.println("---------------------------");		
//		/*
//		 * strategy design pattern to dynamically change classifier algorithms from use case to use case
//		 * ALevelAnalyzer will run J48
//		 * OLevelAnalyzwe will run NaiveBayes
//		 */
//		CAnalyzer analyzer = new CALevelAnalyzer();
//		analyzer.setClassifierType(new CJ48Classifier());	
//
//		Instances subject_1 = new Instances(train);
//		J48 subject_1_model = (J48) analyzer.classifierType.buildClassifier(subject_1, "2,3,5,6,8,9,11,12,14,15,17,18,20,21");
//		CFilter.saveModel(subject_1_model, "F:/Projects/models/subject_1_J48.model");
//		analyzer.drawTree(subject_1_model);
//
//		Instances subject_2 = new Instances(train);
//		J48 subject_2_model = (J48) analyzer.classifierType.buildClassifier(subject_2, "1,3,4,6,7,9,10,12,13,15,16,18,19,21");
//		CFilter.saveModel(subject_2_model, "F:/Projects/models/subject_2_J48.model");
//		analyzer.drawTree(subject_2_model);
//
//		Instances subject_3 = new Instances(train);
//		J48 subject_3_model = (J48) analyzer.classifierType.buildClassifier(subject_3, "1,2,4,5,7,8,10,11,13,14,16,17,19,20");
//		CFilter.saveModel(subject_3_model, "F:/Projects/models/subject_3_J48.model");
//		analyzer.drawTree(subject_3_model);
//
//
//		Instances test = CFilter.retrieveDatasetFromDatabase("select * from ol_input", "root", "");	//retrieve by table name
//
//		Instances toPredict = new Instances(test);
//
//		test = CFilter.removeAttributesByIndices(test, "2-last");			//now "test" will contain only the index numbers
//		test = CFilter.addAttributes(test, "subject_1_final","nominal", "last", "F,S,C,B,A");
//		test = CFilter.addAttributes(test, "subject_2_final","nominal", "last", "F,S,C,B,A");
//		test = CFilter.addAttributes(test, "subject_3_final","nominal", "last", "F,S,C,B,A");
//
//		Instances predicted = new Instances(test);		//"predicted" will hold the index numbers with their three final results=null (predicted)
//
//		predicted.setClassIndex(predicted.numAttributes()-3);
//		System.out.print(CAnalyzer.predict(toPredict, subject_1_model, "subject_1_final", "1,3,4,6,7,9,10,12,13,15,16,18,19",predicted));
//
//		predicted.setClassIndex(predicted.numAttributes()-2);
//		System.out.print(CAnalyzer.predict(toPredict, subject_2_model, "subject_2_final", "1,2,4,5,7,8,10,11,13,14,16,17,19",predicted));
//
//		predicted.setClassIndex(predicted.numAttributes()-1);
//		System.out.print(CAnalyzer.predict(toPredict, subject_3_model, "subject_3_final",  "1,2,3,5,6,8,9,11,12,14,15,17,18",predicted));
//
//		CFilter.arff2Database(predicted, "al_output","jdbc:mysql://localhost:3306/class","root",""); 	//store the predicted results (three final results with index numbers) to the data base
//		CFilter.saveCSV(predicted, "F:/Projects/models/OLevel_J48_Prediction.csv");
//
//		long endTime = System.nanoTime();
//		System.out.println("Took "+(endTime - startTime) + " ns");
//		
//	}
//
//}
