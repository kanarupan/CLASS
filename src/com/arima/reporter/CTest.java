package com.arima.reporter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Range;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.AddValues;
import weka.filters.unsupervised.attribute.StringToWordVector;

import com.arima.analyzer.CALevelAnalyzer;
import com.arima.analyzer.CAnalyzer;
import com.arima.classifier.CJ48Classifier;
import com.arima.classifier.CNaiveBayesClassifier;
import com.arima.filter.CFilter;

public class CTest {

	public static void main (String args[]) throws Exception{
 
		CAnalyzer analyzer = new CALevelAnalyzer();
		analyzer.setClassifierType(new CJ48Classifier());
		
		Instances trainInsts = CFilter.loadCSV("C:/Users/Chamika/Desktop/JHC_OLEVEL_TAMIL/train.arff");
		
		trainInsts = CFilter.removeAttributesByNames(trainInsts, "admission_no,name");
		trainInsts = CFilter.numeric2nominal(trainInsts, "first-last");
		trainInsts = CFilter.changeAttributesNominalValues(trainInsts, "1,2,3,4,5,6", "1,2,3,4,5");
		trainInsts = CFilter.renameAttributesValues(trainInsts, "1,2,3,4,5,6", "1,2,3,4,5", "F,S,C,B,A");
		trainInsts.setClassIndex(trainInsts.numAttributes()-1);		
		trainInsts.deleteWithMissingClass();
	
		Instances subject_1 = new Instances(trainInsts);
		J48 jhc_tamil_model = (J48) analyzer.classifierType.buildClassifier(subject_1, "");
		CFilter.saveModel(jhc_tamil_model, "C:/JSF/CLASS/jhc_tamil_model.model");

		Instances classifyInsts = CFilter.loadCSV("C:/Users/Chamika/Desktop/JHC_OLEVEL_TAMIL/test.arff");
		
		Instances indexNumbers = new Instances(classifyInsts);
		indexNumbers = CFilter.removeAttributesByIndices(indexNumbers, "2-last");
		
		classifyInsts = CFilter.removeAttributesByNames(classifyInsts, "admission_no,name,tamil_term_6");
		classifyInsts = CFilter.numeric2nominal(classifyInsts, "first-last");
		classifyInsts = CFilter.changeAttributesNominalValues(classifyInsts, "1,2,3,4,5", "1,2,3,4,5");
		classifyInsts = CFilter.renameAttributesValues(classifyInsts, "1,2,3,4,5", "1,2,3,4,5", "F,S,C,B,A");
		
		Instances toPredict = new Instances(classifyInsts);
		
		Instances predicted = CFilter.addAttributes(indexNumbers, "tamil_final","nominal", "last", "F,S,C,B,A");
		predicted.setClassIndex(predicted.numAttributes()-1);
		
		System.out.print(CAnalyzer.predict(toPredict, jhc_tamil_model, "tamil_final", "",predicted));

//		CFilter.arff2Database(predicted, "al_output","jdbc:mysql://localhost:3306/class","root",""); 	//store the predicted results (three final results with index numbers) to the data base
		CFilter.saveCSV(predicted, "C:/JSF/CLASS/ol_tamil_prediction.csv");
		
	}

}