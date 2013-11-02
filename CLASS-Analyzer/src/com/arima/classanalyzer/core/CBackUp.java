package com.arima.classanalyzer.core;
//package com.arima.analyzer;
//
//import weka.classifiers.bayes.NaiveBayes;
//import weka.classifiers.functions.MultilayerPerceptron;
//import weka.core.Instances;
//
//import com.arima.classifier.CMultiLayerPerceptronClassifier;
//import com.arima.classifier.CNaiveBayesClassifier;
//import com.arima.filter.CFilter;
//
//public class CBackUp {
//	
//	public static void main(String args[]) throws Exception{
//
//	//------------------------------------------Naive Bayes------------------------
//			long startTime1 = System.nanoTime();
//			/*
//			 * Load the instances from data base
//			 * Can write query that involves many tables
//			 * Strictly follow SQL syntax
//			 */
//			Instances train = CFilter.retrieveDatasetFromDatabase("select * from al_model", "root", "");
//			
//
//			/*
//			 * There are two methods to remove the unnecessary attributes from the data set
//			 * train = CFilter.removeAttributesByIndices(train, "1");
//			 * 			OR
//			 * train = CFilter.removeAttributesByNames(train, "index_no");
//			 */				
//			train = CFilter.removeAttributesByNames(train, "index_no");
//
//			/*
//			 * convert numerical values to nominal values
//			 */
//			train = CFilter.numeric2nominal(train, "first-last");
//			train = CFilter.changeAttributesNominalValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", "1,2,3,4,5");
//			train = CFilter.renameAttributesValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", "1,2,3,4,5", "F,S,C,B,A");
//			System.out.println("---------------------------");
//			System.out.println("Train Data!");
//			System.out.println(train);
//			System.out.println("---------------------------");		
//			/*
//			 * strategy design pattern to dynamically change classifier algorithms from use case to use case
//			 * ALevelAnalyzer will run J48
//			 * OLevelAnalyzwe will run NaiveBayes
//			 */
//			CAnalyzer analyzer = new CALevelAnalyzer();
//			analyzer.setClassifierType(new CNaiveBayesClassifier());	
//
//			Instances subject_1 = new Instances(train);
//			NaiveBayes subject_1_model = (NaiveBayes) analyzer.classifierType.buildClassifier(subject_1, "2,3,5,6,8,9,11,12,14,15,17,18,20,21");
//			CFilter.saveModel(subject_1_model, "C:/JSF/CLASS/subject_1_NaiveBayes.model");
//			//		analyzer.drawTree(subject_1_model);
//
//			Instances subject_2 = new Instances(train);
//			NaiveBayes subject_2_model = (NaiveBayes) analyzer.classifierType.buildClassifier(subject_2, "1,3,4,6,7,9,10,12,13,15,16,18,19,21");
//			CFilter.saveModel(subject_2_model, "C:/JSF/CLASS/subject_2_NaiveBayes.model");
//			//		analyzer.drawTree(subject_2_model);
//
//			Instances subject_3 = new Instances(train);
//			NaiveBayes subject_3_model = (NaiveBayes) analyzer.classifierType.buildClassifier(subject_3, "1,2,4,5,7,8,10,11,13,14,16,17,19,20");
//			CFilter.saveModel(subject_3_model, "C:/JSF/CLASS/subject_3_NaiveBayes.model");
//			//		analyzer.drawTree(subject_3_model);
//
//
//			Instances test = CFilter.retrieveDatasetFromDatabase("select * from al_input", "root", "");	//retrieve by table name
//
//			Instances toPredict = new Instances(test);
//
//			test = CFilter.removeAttributesByIndices(test, "2-last");			//now "test" will contain only the index numbers
//			test = CFilter.addAttributes(test, "subject_1_final","nominal", "last", "F,S,C,B,A");
//			test = CFilter.addAttributes(test, "subject_2_final","nominal", "last", "F,S,C,B,A");
//			test = CFilter.addAttributes(test, "subject_3_final","nominal", "last", "F,S,C,B,A");
//
//			Instances predicted = new Instances(test);		//"predicted" will hold the index numbers with their three final results=null (predicted)
//
//			predicted.setClassIndex(predicted.numAttributes()-3);
//			System.out.print(CAnalyzer.predict(toPredict, subject_1_model, "subject_1_final", "1,3,4,6,7,9,10,12,13,15,16,18,19",predicted));
//
//			predicted.setClassIndex(predicted.numAttributes()-2);
//			System.out.print(CAnalyzer.predict(toPredict, subject_2_model, "subject_2_final", "1,2,4,5,7,8,10,11,13,14,16,17,19",predicted));
//
//			predicted.setClassIndex(predicted.numAttributes()-1);
//			System.out.print(CAnalyzer.predict(toPredict, subject_3_model, "subject_3_final",  "1,2,3,5,6,8,9,11,12,14,15,17,18",predicted));
//
//			CFilter.arff2Database(predicted, "al_output","jdbc:mysql://localhost:3306/class","root",""); 	//store the predicted results (three final results with index numbers) to the data base
//			CFilter.saveCSV(predicted, "C:/JSF/CLASS/ALevel_MathsStream_NaiveBayes_Prediction.csv");
//			long endTime1 = System.nanoTime();
//			System.out.println("Took "+(endTime1 - startTime1) + " ns");
//			
//			
//			//----------------------------------------------------- MultiLayerPerceptron--------------------------
//			
//			long startTime3 = System.nanoTime();
//			Instances train2 = CFilter.retrieveDatasetFromDatabase("select * from al_model", "root", "");
//
//			/*
//			 * There are two methods to remove the unnecessary attributes from the data set
//			 * train = CFilter.removeAttributesByIndices(train, "1");
//			 * 			OR
//			 * train = CFilter.removeAttributesByNames(train, "index_no");
//			 */				
//			train2 = CFilter.removeAttributesByNames(train2, "index_no");
//
//			/*
//			 * convert numerical values to nominal values
//			 */
//			train2 = CFilter.numeric2nominal(train2, "first-last");
//			train2 = CFilter.changeAttributesNominalValues(train2, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", "1,2,3,4,5");
//			train2 = CFilter.renameAttributesValues(train2, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", "1,2,3,4,5", "F,S,C,B,A");
//			System.out.println("---------------------------");
//			System.out.println("Train Data!");
//			System.out.println(train2);
//			System.out.println("---------------------------");		
//			/*
//			 * strategy design pattern to dynamically change classifier algorithms from use case to use case
//			 * ALevelAnalyzer will run J48
//			 * OLevelAnalyzwe will run NaiveBayes
//			 */
//			CAnalyzer analyzer2 = new CALevelAnalyzer();
//			analyzer2.setClassifierType(new CMultiLayerPerceptronClassifier());	
//
//			Instances subject_12 = new Instances(train2);
//			MultilayerPerceptron subject_12_model = (MultilayerPerceptron) analyzer2.classifierType.buildClassifier(subject_12, "2,3,5,6,8,9,11,12,14,15,17,18,20,21");
//			CFilter.saveModel(subject_12_model, "C:/JSF/CLASS/subject_1_MultipLayer.model");
////			analyzer.drawTree(subject_12_model);
//
//			Instances subject_22 = new Instances(train2);
//			MultilayerPerceptron subject_22_model = (MultilayerPerceptron) analyzer2.classifierType.buildClassifier(subject_22, "1,3,4,6,7,9,10,12,13,15,16,18,19,21");
//			CFilter.saveModel(subject_22_model, "C:/JSF/CLASS/subject_2_MultiLayer.model");
////			analyzer.drawTree(subject_22_model);
//
//			Instances subject_32 = new Instances(train2);
//			MultilayerPerceptron subject_32_model = (MultilayerPerceptron) analyzer2.classifierType.buildClassifier(subject_32, "1,2,4,5,7,8,10,11,13,14,16,17,19,20");
//			CFilter.saveModel(subject_32_model, "C:/JSF/CLASS/subject_3_MultiLayer.model");
////			analyzer.drawTree(subject_32_model);
//
//
//			Instances test2 = CFilter.retrieveDatasetFromDatabase("select * from al_input", "root", "");	//retrieve by table name
//
//			Instances toPredict2 = new Instances(test2);
//
//			test2 = CFilter.removeAttributesByIndices(test2, "2-last");			//now "test" will contain only the index numbers
//			test2 = CFilter.addAttributes(test2, "subject_1_final","nominal", "last", "F,S,C,B,A");
//			test2 = CFilter.addAttributes(test2, "subject_2_final","nominal", "last", "F,S,C,B,A");
//			test2 = CFilter.addAttributes(test2, "subject_3_final","nominal", "last", "F,S,C,B,A");
//
//			Instances predicted2 = new Instances(test2);		//"predicted" will hold the index numbers with their three final results=null (predicted)
//
//			predicted2.setClassIndex(predicted2.numAttributes()-3);
//			System.out.print(CAnalyzer.predict(toPredict2, subject_12_model, "subject_1_final", "1,3,4,6,7,9,10,12,13,15,16,18,19",predicted2));
//
//			predicted2.setClassIndex(predicted2.numAttributes()-2);
//			System.out.print(CAnalyzer.predict(toPredict2, subject_22_model, "subject_2_final", "1,2,4,5,7,8,10,11,13,14,16,17,19",predicted2));
//
//			predicted2.setClassIndex(predicted2.numAttributes()-1);
//			System.out.print(CAnalyzer.predict(toPredict2, subject_32_model, "subject_3_final",  "1,2,3,5,6,8,9,11,12,14,15,17,18",predicted2));
//
//			CFilter.arff2Database(predicted2, "al_output","jdbc:mysql://localhost:3306/class","root",""); 	//store the predicted results (three final results with index numbers) to the data base
//			CFilter.saveCSV(predicted2, "C:/JSF/CLASS/ALevel_MathsStream_MultiLayer_Prediction.csv");
//			
//			long endTime3 = System.nanoTime();
//			System.out.println("Took "+(endTime3 - startTime3) + " ns");
//	
//	}
//			
//}
