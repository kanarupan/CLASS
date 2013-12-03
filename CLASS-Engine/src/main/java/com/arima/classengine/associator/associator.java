package com.arima.classengine.associator;

import java.util.ArrayList;
import java.util.List;

import weka.associations.Apriori;
import weka.associations.AssociationRule;
import weka.core.Instances;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;

public class associator {
	
	private static String statFileName;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		int schoolNo = 11086;
		int grade = 11;
		int term = 1;
		int bins=3;
		List<String> subjects = new ArrayList<String>();
		subjects.add("SAIVISM");
		subjects.add("MATHEMATICS");
		subjects.add("SCIENCE AND TECHNOLOGY");
		subjects.add("TAMIL LANGUAGE");
		subjects.add("ENGLISH LANGUAGE");
		subjects.add("HISTORY");
		subjects.add("INFORMATION AND COMMUNICATION TECHNOLOGY");
		subjects.add("BUSSINESS AND ACCOUNTING");
		
		Instances instances = Utils.prepareTrainData(schoolNo, grade, term, subjects.get(0));
		instances = CFilter.removeAttributesByIndices(instances, "1");
		
		Instances instance = null;
		
		for(int i=1; i< subjects.size(); i++){
			instance = Utils.prepareTrainData(schoolNo, grade, term, subjects.get(i));
			instance = CFilter.removeAttributesByIndices(instance, "1");
			instances = Instances.mergeInstances(instances, instance);
		}
		
		
		
		instances = CFilter.numeric2nominal(instances, "first-last", bins);
		
		
		instances = Utils.changeAttributeNominalRange(instances, Utils.getAttributeLables(bins, true));
		instances = Utils.renameAttributes(instances, bins);
		System.out.println(instances);
		getAssociationRules(instances, statFileName);
System.exit(0);
		


		
		instances = CFilter.removeAttributesByIndices(instances, "1");

		System.out.println(instances);
		
//		Instances instances = CFilter.retrieveDatasetFromDatabase(Utils.createPredictionQuery(10, 1, "MATHEMATICS"), "root", "");
//		instances = CFilter.removeAttributesByIndices(instances, "1");
//		instances = CFilter.numeric2nominal(instances, "first-last", 3);
//		instances = Utils.changeAttributeNominalRange(instances, Utils.getAttributeLables(3, true));
//		instances = Utils.renameAttributes(instances, 3);
//		
//		instances = Instances.mergeInstances(instances, 
//				Utils.renameAttributes(Utils.changeAttributeNominalRange(CFilter.numeric2nominal(
//						CFilter.removeAttributesByIndices(
//								CFilter.retrieveDatasetFromDatabase(
//										Utils.createPredictionQuery(10, 1, "SAIVISM"), "root", ""), "1"), "first-last", 3), Utils.getAttributeLables(3, true)), 3));
		
//		System.out.println(instances); System.exit(0);
		
		
		
		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "MATHEMATICS", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "MATHEMATICS", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "MATHEMATICS", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "MATHEMATICS", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "MATHEMATICS", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "SCIENCE AND TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "SCIENCE & TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "SCIENCE & TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "SCIENCE & TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "SCIENCE & TECHNOLOGY", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "SAIVISM", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "SAIVISM", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "SAIVISM", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "SAIVISM", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "SAIVISM", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "TAMIL LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "TAMIL LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "TAMIL LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "TAMIL LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "TAMIL LANGUAGE", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "ENGLISH LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "ENGLISH LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "ENGLISH LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "ENGLISH LANGUAGE", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "ENGLISH LANGUAGE", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "HISTORY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "HISTORY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "HISTORY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "HISTORY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "HISTORY", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "BUSSINESS & ACCOUNTING", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "BUSSINESS & ACCOUNTING", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "BUSSINESS & ACCOUNTING", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "BUSSINESS & ACCOUNTING", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "BUSSINESS & ACCOUNTING", 3);
//		
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 10, 3, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 1, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 2, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
//		updateIntraSubjectsAssociationRulesStats(2008, 11, 3, "INFORMATION AND COMMUNICATION TECHNOLOGY", 3);
		
	}
	
	public static void updateIntraSubjectsAssociationRulesStats(int year, int grade, int term, String subject, int bins) throws Exception{
		
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
		ap.setNumRules(1000);
		ap.setMinMetric(0.90);
//		ap.setVerbose(true);
//		ap.setLowerBoundMinSupport(0.90);
//		ap.setUpperBoundMinSupport(1);
		ap.buildAssociations(instances);
		List<AssociationRule> rule =  ap.getAssociationRules().getRules();
		System.out.println(ap.getAssociationRules().getNumRules());
		
		message = "Number of rules : " + ap.getAssociationRules().getNumRules()
				+ "\n\n";
		
		for(int i=0; i< rule.size(); i++){
			message = message + "" + rule.get(i).toString() + "\n\n";
			
			System.out.println(rule.get(i).toString());
		}
		 statFileName = "F:/Projects/CLASS/CLASS-Engine/data/Association Rules/associations.txt";
			CFilter.appendfile(statFileName, message);
		
		
	}

}
