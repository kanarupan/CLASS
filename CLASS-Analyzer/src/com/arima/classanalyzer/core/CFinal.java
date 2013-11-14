package com.arima.classanalyzer.core;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.CancelablePrintJob;

import org.omg.PortableInterceptor.INACTIVE;

import com.arima.classanalyzer.classifier.CJ48Classifier;
import com.arima.classanalyzer.filter.CFilter;
import com.arima.classanalyzer.core.Model;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.DatabaseConnection;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

public class CFinal {
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<Integer> marks = new ArrayList<Integer>();
		marks.add(93);
		marks.add(82);
		marks.add(96);
		marks.add(71);
		marks.add(83);

		System.out.println(predictNextTerm(null, 2008, 11, 3, "MATHEMATICS", 4545, marks));
		
		
		
	}
	
	public static String predictNextTerm(Connection conn, int year, int grade,int term, String subject,  int index_no, ArrayList<Integer> marks) throws Exception{
		
		System.out.println("Retrieving dataset to be predicted");
		Instances test = CFilter.createInstances(11, marks);
		
		Model model = loadModelFromDatabase(year, grade, term, subject);
		Instances predicted = CAnalyzer.predict(test, model.getClassifier(), model.getBins());
		System.out.println(predicted);

		return predicted.instance(0).stringValue(1);
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
            case 2:  lables = "F,S";
                     break;
            case 3:  lables = "F,S,C";
                     break;
            case 4:  lables = "F,S,C,B";
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
	
	public static Model loadModelFromDatabase(int year, int grade, int term, String subject) throws Exception{

		Model model = new Model();
		int bins=-1;
		Classifier cls=null;
		DatabaseConnection dc = new DatabaseConnection();
		dc.setDatabaseURL("jdbc:mysql://localhost:3306/class");
		dc.setUsername("root");
		dc.setPassword("");
		dc.connectToDatabase();
		
		dc.execute("SELECT model, bins, type FROM  class_analyzer_classifier where year = "+ year +" and grade = " + grade
				+" and term = " 
				+term+ " and subject = '" +subject+"'");

		ResultSet rs = dc.getResultSet();
		
		while(rs.next()){

			InputStream is = rs.getBinaryStream("model");
			cls =  (Classifier) weka.core.SerializationHelper.read(is);
			
			model.setYear(year);
			model.setGrade(grade);
			model.setTerm(term);
			model.setSubject(subject);
			model.setClassifier(cls);
			model.setType(rs.getString("type"));
			model.setBins(rs.getInt("bins"));
		}
		dc.close();
		return model;
	}
}
