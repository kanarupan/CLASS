package com.arima.classengine.comparator;

import weka.core.Instances;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;

public class HammingDistance {
	
	private String s1,s2;
	private double distance;
	
	private static double AtoB;
	private static double AtoC;
	private static double AtoS;
	private static double AtoF;
	
	private static double BtoA;
	private static double BtoC;
	private static double BtoS ;
	private static double BtoF;
	
	private static double CtoA;
	private static double CtoB;
	private static double CtoS;
	private static double CtoF ;
	
	private static double StoA;
	private static double StoB;
	private static double StoC;
	private static double StoF;
	
	private static double FtoA;
	private static double FtoB;
	private static double FtoC;
	private static double FtoS;
	
	public static void main(String args[]) throws Exception{

		initializeMeans(11089, "HISTORY");
		getHammingSimilarity(11089, 11, 3, 11, 2, "HISTORY");
	}

	public HammingDistance(String s1, String s2) throws Exception{
		this.s1 = s1;
		this.s2 = s2;
		
		}

	
	public static void getHammingSimilarity(int schoolNo, int grade1, int term1, int grade2, int term2, String subject) throws Exception {
		
		Instances train = CFilter.retrieveDatasetFromDatabase(Utils.createPredictionQuery(schoolNo, grade1, term1, subject), "root", "");
		Instances train2 = CFilter.retrieveDatasetFromDatabase(Utils.createPredictionQuery(schoolNo, grade2, term2, subject), "root", "");
		
		
		
		train2 = CFilter.removeAttributesByIndices(train2, "1");
		train = Instances.mergeInstances(train, train2);
		train.deleteWithMissing(1);
		train.deleteWithMissing(2);
		
		
		
		int bins = 5;
		train = CFilter.removeAttributesByIndices(train, "1");
		train = CFilter.numeric2nominal(train, "first-last",bins);
		train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(bins, true));
		train = Utils.renameAttributes(train, bins);
		
		System.out.println(train);
		String term_sequence = "";
		String general_sequence = "";
		
		for (int i = 0; i < train.size(); i++) {
			term_sequence = term_sequence + train.instance(i).stringValue(0);
			general_sequence = general_sequence + train.instance(i).stringValue(1);
		}
		
		System.out.println(term_sequence);
		System.out.println(general_sequence);
		
		HammingDistance hd = new HammingDistance(term_sequence, general_sequence);
		System.out.println("Distance is : " + hd.getDistance());
		System.out.println("Similarity is : " + (hd.getSimilarity()));
	}
	
	
	public double getSimilarity(){
		return 100-getDistance();
	}
	
	public double getDistance() {
		
		distance = 0;
		
		
		// check preconditions
		if (s1 == null || s2 == null || s1.length() != s2.length()) {
			throw new IllegalArgumentException();
		}

		// compute hamming distance
		for (int i = 0; i < s1.length(); i++) {
			
			if (s1.charAt(i) == s2.charAt(i)) {   
			}
			else {
				if (s1.charAt(i) == 'A' && s2.charAt(i)=='B'){
					distance = distance + AtoB; 
				}
				if (s1.charAt(i) == 'A' && s2.charAt(i)=='C'){
					distance = distance + AtoC; 
				}
				if (s1.charAt(i) == 'A' && s2.charAt(i)=='S'){
					distance = distance + AtoS; 
				}
				if (s1.charAt(i) == 'A' && s2.charAt(i)=='F'){
					distance = distance + AtoF; 
				}

				if (s1.charAt(i) == 'B' && s2.charAt(i)=='A'){
					distance = distance + BtoA; 
				}
				if (s1.charAt(i) == 'B' && s2.charAt(i)=='C'){
					distance = distance + BtoC; 
				}
				if (s1.charAt(i) == 'B' && s2.charAt(i)=='S'){
					distance = distance + BtoS; 
				}
				if (s1.charAt(i) == 'B' && s2.charAt(i)=='F'){
					distance = distance + BtoF; 
				}

				if (s1.charAt(i) == 'C' && s2.charAt(i)=='A'){
					distance = distance + CtoA; 
				}
				if (s1.charAt(i) == 'C' && s2.charAt(i)=='B'){
					distance = distance + CtoB; 
				}
				if (s1.charAt(i) == 'C' && s2.charAt(i)=='S'){
					distance = distance + CtoS; 
				}
				if (s1.charAt(i) == 'C' && s2.charAt(i)=='F'){
					distance = distance + CtoF; 
				}
				
				if (s1.charAt(i) == 'S' && s2.charAt(i)=='A'){
					distance = distance + StoA; 
				}
				if (s1.charAt(i) == 'S' && s2.charAt(i)=='B'){
					distance = distance + StoB; 
				}
				if (s1.charAt(i) == 'S' && s2.charAt(i)=='C'){
					distance = distance + StoC; 
				}
				if (s1.charAt(i) == 'S' && s2.charAt(i)=='F'){
					distance = distance + StoF; 
				}
				
				

				if (s1.charAt(i) == 'F' && s2.charAt(i)=='A'){
					distance = distance + FtoA; 
				}
				if (s1.charAt(i) == 'F' && s2.charAt(i)=='B'){
					distance = distance + FtoB; 
				}
				if (s1.charAt(i) == 'F' && s2.charAt(i)=='C'){
					distance = distance + FtoC; 
				}
				if (s1.charAt(i) == 'F' && s2.charAt(i)=='S'){
					distance = distance + FtoS; 
				}

			}
		}
		return (distance)/(s1.length());

	}
	
	public static void initializeMeans(int schoolNo, String subject) throws Exception{
		
		  double A = CFilter.retrieveDatasetFromDatabase(
				Utils.createComparisionQuery(
						schoolNo, 11, 3, subject, 75, 100), "root", "").attributeStats(1).numericStats.mean;

		  double B = CFilter.retrieveDatasetFromDatabase(
				Utils.createComparisionQuery(
						schoolNo, 11, 3, subject, 65, 74), "root", "").attributeStats(1).numericStats.mean;

		  double C = CFilter.retrieveDatasetFromDatabase(
				Utils.createComparisionQuery(
						schoolNo, 11, 3, subject, 55, 64), "root", "").attributeStats(1).numericStats.mean;

		  double S = CFilter.retrieveDatasetFromDatabase(
				Utils.createComparisionQuery(
						schoolNo, 11, 3, subject, 35, 54), "root", "").attributeStats(1).numericStats.mean;

		  double F = CFilter.retrieveDatasetFromDatabase(
				Utils.createComparisionQuery(
						schoolNo, 11, 3, subject, 0, 34), "root", "").attributeStats(1).numericStats.mean;

			
							
							
			if(A==0 || Double.isNaN(A))
				A = (75+100)/2;
			if(B==0 || Double.isNaN(B))
				B = (74+65)/2;
			if(C==0 || Double.isNaN(C))
				C = (64+55)/2;
			if(S==0 || Double.isNaN(S))
				S = (54+35)/2;
			if(F==0 || Double.isNaN(F))
				F = (34+0)/2;
			
			AtoB = A-B;
			AtoC = A-C;
			AtoS = A-S;
			AtoF = A-F;
			
			BtoA = A-B;
			BtoC = B-C;
			BtoS = B-S;
			BtoF = B-F;
			
			CtoA = A-C;
			CtoB = B-C;
			CtoS = C-S;
			CtoF = C-F;
			
			StoA = A-S;
			StoB = B-S;
			StoC = C-S;
			StoF = S-F;
			
			FtoA = A-F;
			FtoB = B-F;
			FtoC = C-F;
			FtoS = S-F;
			

			
	}
}
