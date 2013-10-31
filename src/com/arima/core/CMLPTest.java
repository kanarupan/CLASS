package com.arima.core;
//package com.arima.analyzer;
//import com.arima.classifier.CJ48Classifier;
//import com.arima.classifier.CLinearRegressionClassifier;
//import com.arima.classifier.CMultiLayerPerceptronClassifier;
//import com.arima.classifier.CNaiveBayesClassifier;
//import com.arima.filter.CFilter;
//
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.functions.LinearRegression;
//import weka.classifiers.functions.MultilayerPerceptron;
//import weka.classifiers.trees.J48;
//import weka.core.Instances;
//import weka.core.converters.ConverterUtils.DataSource;
//import weka.filters.Filter;
//import weka.filters.unsupervised.attribute.ReplaceMissingValues;
//
//public class CMLPTest {
//
//	public static void main(String[] args) throws Exception {
//	
//
//		String five = "1,2,3,4,5";
//		String fives = "F,S,C,B,A";
//		String four = "1,2,3,4";
//		String fours = "F,S,C,A";
//		String three = "1,2,3";
//		String threes = "F,S,A";
//		String two = "1,2";
//		String twos = "F,A";
//		
//		long startTime = System.nanoTime();
//		Instances train = CFilter.retrieveDatasetFromDatabase("select * from some", "root", "");
//
//		train = CFilter.removeAttributesByNames(train, "index_no");
//		
//
//
//		/*
//		 * convert numerical values to nominal values
//		 */
//		train = CFilter.numeric2nominal(train, "first-last");
//		
//		ReplaceMissingValues rmv = new ReplaceMissingValues();
//		rmv.setInputFormat(train);
//		train = Filter.useFilter(train, rmv);
//		
//		
//		train = CFilter.changeAttributesNominalValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", three);
//		train = CFilter.renameAttributesValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", three, threes);
//		System.out.println("---------------------------");
//		System.out.println("Train Data!");
//		System.out.println(train);
//		System.out.println("---------------------------");		
//		/*
//		 * strategy design pattern to dynamically change classifier algorithms from use case to use case
//		 * ALevelAnalyzer will run J48
//		 * OLevelAnalyzwe will run NaiveBayes
//		 */
//		CAnalyzer analyzer = new CALevelAnalyzer();
//		analyzer.setClassifierType(new CMultiLayerPerceptronClassifier());	
//
//		Instances subject_1 = new Instances(train);
//		MultilayerPerceptron subject_1_model = (MultilayerPerceptron) analyzer.classifierType.buildClassifier(subject_1, "2,3,5,6,8,9,11,12,14,15,16,17,18,20,21");
////		CFilter.saveModel(subject_1_model, "C:/JSF/CLASS/OL_subject_1_J48.model");
//
//		Instances subject_2 = new Instances(train);
//		MultilayerPerceptron subject_2_model = (MultilayerPerceptron) analyzer.classifierType.buildClassifier(subject_2, "1,3,4,6,7,9,10,12,13,15,16,17,18,19,21");
////		CFilter.saveModel(subject_2_model, "C:/JSF/CLASS/OL_subject_2_J48.model");
//
//		Instances subject_3 = new Instances(train);
//		MultilayerPerceptron subject_3_model = (MultilayerPerceptron) analyzer.classifierType.buildClassifier(subject_3, "1,2,4,5,7,8,10,11,13,14,16,17,18,19,20");
////		CFilter.saveModel(subject_3_model, "C:/JSF/CLASS/OL_subject_3_J48.model");
//
//
//		Instances test = CFilter.retrieveDatasetFromDatabase("select * from ol_input", "root", "");	//retrieve by table name
//
//		Instances toPredict = new Instances(test);
//
//		test = CFilter.removeAttributesByIndices(test, "2-last");			//now "test" will contain only the index numbers
//		test = CFilter.addAttributes(test, "subject_1_final","nominal", "last", threes);
//		test = CFilter.addAttributes(test, "subject_2_final","nominal", "last", threes);
//		test = CFilter.addAttributes(test, "subject_3_final","nominal", "last", threes);
//
//		Instances predicted = new Instances(test);		//"predicted" will hold the index numbers with their three final results=null (predicted)
//
//		predicted.setClassIndex(predicted.numAttributes()-3);
//		System.out.print(CAnalyzer.predict(toPredict, subject_1_model, "subject_1_final", "1,3,4,6,7,9,10,12,13,15,16,17,18,19",predicted));
//
//		predicted.setClassIndex(predicted.numAttributes()-2);
//		System.out.print(CAnalyzer.predict(toPredict, subject_2_model, "subject_2_final", "1,2,4,5,7,8,10,11,13,14,16,17,18,19",predicted));
//
//		predicted.setClassIndex(predicted.numAttributes()-1);
//		System.out.print(CAnalyzer.predict(toPredict, subject_3_model, "subject_3_final",  "1,2,3,5,6,8,9,11,12,14,15,17,18,19",predicted));
//
////		CFilter.arff2Database(predicted, "al_output","jdbc:mysql://localhost:3306/class","root",""); 	//store the predicted results (three final results with index numbers) to the data base
////		CFilter.saveCSV(predicted, "C:/JSF/CLASS/OLevel_J48_Prediction.csv");
//
//		long endTime = System.nanoTime();
//		System.out.println("Took "+(endTime - startTime) + " ns");
//		
//	}
//
//}
