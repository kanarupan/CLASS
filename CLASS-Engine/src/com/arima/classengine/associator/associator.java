package com.arima.classengine.associator;

import java.util.ArrayList;
import java.util.List;

import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.associations.AssociationRules;
import weka.core.Instances;

import com.arima.classengine.classifier.CClassifier;
import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;
import com.mysql.jdbc.Util;

public class associator {
	
	private static String statFileName;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		Instances instances = CFilter.retrieveDatasetFromDatabase(Utils.createPredictionQuery(10, 1, "MATHEMATICS"), "root", "");
//		instances = CFilter.removeAttributesByIndices(instances, "1");
		instances = CFilter.numeric2nominal(instances, "first-last", 3);
		instances = Utils.changeAttributeNominalRange(instances, Utils.getAttributeLables(3, true));
		instances = Utils.renameAttributes(instances, 3);
		
		instances = Instances.mergeInstances(instances, 
				Utils.renameAttributes(Utils.changeAttributeNominalRange(CFilter.numeric2nominal(
						CFilter.removeAttributesByIndices(
								CFilter.retrieveDatasetFromDatabase(
										Utils.createPredictionQuery(10, 1, "SAIVISM"), "root", ""), "1"), "first-last", 3), Utils.getAttributeLables(3, true)), 3));
		
		System.out.println(instances); System.exit(0);
		
		updateAssociationRulesStats(2008, 11, 2, "MATHEMATICS", 3);
		updateAssociationRulesStats(2008, 10, 3, "MATHEMATICS", 3);
		updateAssociationRulesStats(2008, 11, 1, "MATHEMATICS", 3);
		updateAssociationRulesStats(2008, 11, 2, "MATHEMATICS", 3);
		updateAssociationRulesStats(2008, 11, 3, "MATHEMATICS", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "SCIENCE & TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 10, 3, "SCIENCE & TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 11, 1, "SCIENCE & TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 11, 2, "SCIENCE & TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 11, 3, "SCIENCE & TECHNOLOGY", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "SAIVISM", 3);
		updateAssociationRulesStats(2008, 10, 3, "SAIVISM", 3);
		updateAssociationRulesStats(2008, 11, 1, "SAIVISM", 3);
		updateAssociationRulesStats(2008, 11, 2, "SAIVISM", 3);
		updateAssociationRulesStats(2008, 11, 3, "SAIVISM", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "TAMIL LANGUAGE", 3);
		updateAssociationRulesStats(2008, 10, 3, "TAMIL LANGUAGE", 3);
		updateAssociationRulesStats(2008, 11, 1, "TAMIL LANGUAGE", 3);
		updateAssociationRulesStats(2008, 11, 2, "TAMIL LANGUAGE", 3);
		updateAssociationRulesStats(2008, 11, 3, "TAMIL LANGUAGE", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "ENGLISH LANGUAGE", 3);
		updateAssociationRulesStats(2008, 10, 3, "ENGLISH LANGUAGE", 3);
		updateAssociationRulesStats(2008, 11, 1, "ENGLISH LANGUAGE", 3);
		updateAssociationRulesStats(2008, 11, 2, "ENGLISH LANGUAGE", 3);
		updateAssociationRulesStats(2008, 11, 3, "ENGLISH LANGUAGE", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "HISTORY", 3);
		updateAssociationRulesStats(2008, 10, 3, "HISTORY", 3);
		updateAssociationRulesStats(2008, 11, 1, "HISTORY", 3);
		updateAssociationRulesStats(2008, 11, 2, "HISTORY", 3);
		updateAssociationRulesStats(2008, 11, 3, "HISTORY", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "BUSSINESS & ACCOUNTING", 3);
		updateAssociationRulesStats(2008, 10, 3, "BUSSINESS & ACCOUNTING", 3);
		updateAssociationRulesStats(2008, 11, 1, "BUSSINESS & ACCOUNTING", 3);
		updateAssociationRulesStats(2008, 11, 2, "BUSSINESS & ACCOUNTING", 3);
		updateAssociationRulesStats(2008, 11, 3, "BUSSINESS & ACCOUNTING", 3);
		
		updateAssociationRulesStats(2008, 11, 2, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 10, 3, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 11, 1, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 11, 2, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
		updateAssociationRulesStats(2008, 11, 3, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
		
	}
	
	public static void updateAssociationRulesStats(int year, int grade, int term, String subject, int bins) throws Exception{
		
		getAssociationRules(prepareDataForAssociationRuleMining(year, grade, term, subject, 3), statFileName);
	}
	
	public static Instances prepareDataForAssociationRuleMining(int year, int grade, int term, String subject, int bins) throws Exception{
		
		 statFileName = "F:/Projects/CLASS/CLASS-Engine/data/Association Rules/"+ 
				year + " " + subject + " grade " + grade + " term " + term +  " bins " + bins +  ".txt";
		
		Instances instances = Utils.prepareTrainData(grade, term, subject);
		instances = CFilter.removeAttributesByIndices(instances, "1");
		instances = CFilter.numeric2nominal(instances, "first-last", bins);
		
		instances = Utils.changeAttributeNominalRange(instances, Utils.getAttributeLables(bins, true));

		instances = Utils.renameAttributes(instances, bins);
		
		return instances;
	}
	
	public static void getAssociationRules(Instances instances, String statFileName) throws Exception{
		
		String message = null;
		Apriori ap = new Apriori();
		ap.buildAssociations(instances);
		List<AssociationRule> rule =  ap.getAssociationRules().getRules();
		System.out.println(ap.getAssociationRules().getNumRules());
		
		message = "Number of rules : " + ap.getAssociationRules().getNumRules()
				+ "\n\n";
		
		for(int i=0; i< rule.size(); i++){
			message = message + "" + rule.get(i).toString() + "\n";
			System.out.println(rule.get(i).toString());
		}
		
		CFilter.appendfile(statFileName, message);
	}

}
