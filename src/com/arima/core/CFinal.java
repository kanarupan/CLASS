package com.arima.core;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.CancelablePrintJob;

import org.omg.PortableInterceptor.INACTIVE;

import com.arima.classifier.CJ48Classifier;
import com.arima.filter.CFilter;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

public class CFinal {
	
	public static String predictNextTerm(Connection conn, int grade,int term, String subject,  int index_no, ArrayList<Integer> marks) throws Exception{
		
		Statement st = (Statement) conn.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM  class_analyzer_classifier where grade");
        Classifier cls = null;
        while (res.next()) {
        int bins = res.getInt("bins");
        InputStream is = res.getBinaryStream("model");
        cls =  (Classifier) weka.core.SerializationHelper.read(is);
        CALevelAnalyzer.setBinSize(bins);
        }
		
		System.out.println("Retrieving dataset to be predicted");
		Instances test = CFilter.createInstances(11, marks);
		
		
//		Instances train = CFilter.retrieveDatasetFromDatabase("select * from ol_model", "root", "");
//		System.out.println("Retrieving Instances from database");
//		System.out.println(train);
//		System.exit(0);
		
//		Classifier model = CAnalyzer.getModel(train);
		Classifier model = cls;
		CFilter.saveModel(model, "C:/JSF/CLASS/OL_subject_1_J48.model");
		//		analyzer.drawTree(subject_1_model);
//				System.exit(0);
//		model = CFilter.loadModel("C:/JSF/CLASS/OL_subject_1_J48.model");
		
//		Instances test = CFilter.retrieveDatasetFromDatabase("select * from ol_maths_input", "root", "");	//retrieve by table name
		
		Instances predicted = CAnalyzer.predict(test, model, CAnalyzer.getBinSize());
		System.out.println(predicted);

		//		CFilter.arff2Database(predicted, "al_output","jdbc:mysql://localhost:3306/class","root",""); 	//store the predicted results (three final results with index numbers) to the data base
//		CFilter.saveCSV(predicted, "C:/JSF/CLASS/OLevel_J48_Prediction.csv");
		
		return predicted.instance(0).stringValue(1);
	}
	
	public static void main(String[] args) throws Exception {
		
		
		
		ArrayList<Integer> marks = new ArrayList<Integer>();
		marks.add(93);
		marks.add(82);
		marks.add(96);
		marks.add(71);
		marks.add(83);
		System.out.println("ssss");
//		System.out.println(CFinal.predictNextTerm(11,5,"maths", 45455, marks));
		
		
	}
	
	public static String getAttributeLables(int bins, boolean isLetter){
       
		String lables;
        if(isLetter){
        	
        switch (bins) {
            case 2:  lables = "1,2";
                     break;
            case 3:  lables = "1,2,3";
                     break;
            case 4:  lables = "1,2,3,4";
                     break;
            case 5:  lables = "1,2,3,4,5";
                     break;
            default: lables = "1,2,3,4,5";
                     break;
        }
        }
        else{
        	
            switch (bins) {
            case 2:  lables = "F,A";
                     break;
            case 3:  lables = "F,S,A";
                     break;
            case 4:  lables = "F,S,C,A";
                     break;
            case 5:  lables = "F,S,C,B,A";
                     break;
            default: lables = "F,S,C,B,A";
                     break;
        }
        	
        }
        
        return lables;
	}

	public static Instances handleMissingValues(Instances data) throws Exception{

		System.out.println("Handling missing values");
		ReplaceMissingValues rmv = new ReplaceMissingValues();
		rmv.setInputFormat(data);
		data = Filter.useFilter(data, rmv);
		System.out.println("Number of instances in dataset : " + data.numInstances());

		return data;
	}
	
	public static Instances renameAttributes(Instances train, int bins){
		
		switch (bins) {

		case 2:  		
			for (int j = 0; j < train.numAttributes(); j++) {
				train.renameAttributeValue(j, 0, "F");
				train.renameAttributeValue(j, 1, "S");
			}
			break;

		case 3:              		
			for (int j = 0; j < train.numAttributes(); j++) {
				train.renameAttributeValue(j, 0, "F");
				train.renameAttributeValue(j, 1, "S");
				train.renameAttributeValue(j, 2, "C");
			}
			break;

		case 4:              		
			for (int j = 0; j < train.numAttributes(); j++) {
				train.renameAttributeValue(j, 0, "F");
				train.renameAttributeValue(j, 1, "S");
				train.renameAttributeValue(j, 2, "C");
				train.renameAttributeValue(j, 3, "B");
			}
			break;

		case 5:              		
			for (int j = 0; j < train.numAttributes(); j++) {
				train.renameAttributeValue(j, 0, "F");
				train.renameAttributeValue(j, 1, "S");
				train.renameAttributeValue(j, 2, "C");
				train.renameAttributeValue(j, 3, "B");
				train.renameAttributeValue(j, 4, "A");
			}
			break;

		default:             		
			for (int j = 0; j < train.numAttributes(); j++) {
				train.renameAttributeValue(j, 0, "F");
				train.renameAttributeValue(j, 1, "S");
				train.renameAttributeValue(j, 2, "C");
				train.renameAttributeValue(j, 3, "B");
				train.renameAttributeValue(j, 4, "A");
			}
			break;
		}
		
		return train;
	}
	
	public static Instances changeAttributeNominalRange(Instances train, String labels) throws Exception{
		
		for (int i = 0; i < train.numAttributes(); i++) {
			train = CFilter.changeAttributeNominalValues(train, i+1, labels);
		}
		
		return train;
	}
}
