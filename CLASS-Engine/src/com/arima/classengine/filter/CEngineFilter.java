package com.arima.classengine.filter;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.CancelablePrintJob;

import org.omg.PortableInterceptor.INACTIVE;

import com.arima.classengine.classifier.CJ48Classifier;
import com.arima.classengine.core.CAnalyzer;
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

public class CEngineFilter {
	
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
	
	public static String createPredictionQuery(int grade, int term, String subject){
		//ex.school_no = 11089 and
		
		String query = "select st.idstudent, mk.makrs as makrs"+String.valueOf(grade)+term +
				" from " +
				"(exam ex  join subject sub on (sub.idsubject=ex.subject_idsubject)) " +
				"join marks mk on (mk.exam_id_exam=ex.id_exam) " +
				"join student_performance stpe on (mk.student_performance_idstudent_performance=stpe.idstudent_performance) " +
				"join student st on (st.idstudent=stpe.student_idstudent) " +
				"where  ex.grade="+ grade+" and ex.term="+ term +" and sub.subject_name='"+subject+"' ";

		return query;
	}
}
