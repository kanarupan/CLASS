package com.arima.classanalyzer.comparator;

import weka.core.Instances;

import com.arima.classanalyzer.filter.CFilter;

public class HammingDistance {

	private String s1,s2;
	private float distance;
	
	/*
	 * A -> 75 or more
	 * B -> 65-75
	 * C -> 55-65
	 * S -> 35-55
	 * F -> 0-35 
	 */
	
	//penalties
	private float AtoB = (75+100)/2 - (65+75)/2 ;
	private float AtoC = (75+100)/2 - (55+65)/2 ;
	private float AtoS = (75+100)/2 - (35+55)/2 ;
	private float AtoF = (75+100)/2 - (0+35)/2 ;
	
	private float BtoA = (75+100)/2 - (65+75)/2 ;
	private float BtoC = (65+75)/2 - (55+65)/2 ;
	private float BtoS = (65+75)/2 - (35+55)/2 ;
	private float BtoF = (65+75)/2 - (0+35)/2 ;
	
	private float CtoA = (75+100)/2 - (55+65)/2 ;
	private float CtoB = (65+75)/2 - (55+65)/2 ;
	private float CtoS = (55+65)/2 - (35+55)/2 ;
	private float CtoF = (55+65)/2 - (0+35)/2 ;
	
	private float StoA = (75+100)/2 - (35+55)/2 ;
	private float StoB = (65+75)/2 - (35+55)/2 ;
	private float StoC = (55+65)/2 - (35+55)/2 ;
	private float StoF = (35+55)/2 - (0+35)/2 ;
	
	private float FtoA = (75+100)/2 - (0+35)/2 ;
	private float FtoB = (65+75)/2 - (0+35)/2 ;
	private float FtoC = (55+65)/2 - (0+35)/2 ;
	private float FtoS = (35+55)/2 - (0+35)/2 ;

	public HammingDistance(String s1, String s2){
		this.s1 = s1;
		this.s2 = s2;
	}

	public static void main(String args[]) throws Exception{
		
		String five = "1,2,3,4,5";
		String fives = "F,S,C,B,A";
		Instances term = CFilter.retrieveDatasetFromDatabase("select * from term_exam", "root", "");
		Instances general = CFilter.retrieveDatasetFromDatabase("select * from general_exam", "root", "");
	
		
		term = CFilter.removeAttributesByNames(term, "index_no");		
		term = CFilter.numeric2nominal(term, "first",5);
		term = CFilter.changeAttributesNominalValues(term, "1", five);
		term = CFilter.renameAttributesValues(term, "1", five, fives);
	
		general = CFilter.removeAttributesByNames(general, "index_no");
		general = CFilter.numeric2nominal(general, "first",5);
		general = CFilter.changeAttributesNominalValues(general, "1", five);
		general = CFilter.renameAttributesValues(general, "1", five, fives);
	
		String term_sequence = "";
		String general_sequence = "";
		
		for (int i = 0; i < term.size(); i++) {
			term_sequence = term_sequence + term.instance(i).stringValue(0);
		}
		
		for (int i = 0; i < general.size(); i++) {
			general_sequence = general_sequence + general.instance(i).stringValue(0);			
		}
		
		System.out.println(term);
		System.out.println(general);
		
		HammingDistance hd = new HammingDistance(term_sequence, general_sequence);
		System.out.println("Distance is : " + hd.getDistance());
		System.out.println("Similarity is : " + (hd.getSimilarity()));
	}
	
	
	public float getSimilarity(){
		return 100-getDistance();
	}
	
	public float getDistance() {
		
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
}
