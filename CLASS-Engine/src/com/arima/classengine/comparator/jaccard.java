package com.arima.classengine.comparator;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import weka.core.Instances;

import com.arima.classengine.filter.CFilter;


class jaccard {

    
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
		String five = "1,2,3,4,5";
		String fives = "F,S,C,B,A";
		Instances train = CFilter.retrieveDatasetFromDatabase("select * from comparator", "root", "");
		train = CFilter.removeAttributesByNames(train, "index_no");
		train = CFilter.numeric2nominal(train, "first-last",5);
		train = CFilter.changeAttributesNominalValues(train, "1,2", five);
		train = CFilter.renameAttributesValues(train, "1,2", five, fives);
		
		int[] term_nominalCounts = train.attributeStats(0).nominalCounts;
		int[] general_nominalCounts = train.attributeStats(1).nominalCounts;
		System.out.println(Arrays.toString(term_nominalCounts));
		System.out.println(Arrays.toString(general_nominalCounts));
	
		int F = Math.min(term_nominalCounts[0], general_nominalCounts[0]);
		int S = Math.min(term_nominalCounts[1], general_nominalCounts[1]);
		int C = Math.min(term_nominalCounts[2], general_nominalCounts[2]);
		int B = Math.min(term_nominalCounts[3], general_nominalCounts[3]);
		int A = Math.min(term_nominalCounts[4], general_nominalCounts[4]);
		
		System.out.println(F + " " + S + " " + C + " " + B + " " + A );
		float similiarity = (float)100 * (F + S + C + B + A)/train.attributeStats(0).totalCount;
		System.out.println("Similarity is : " + similiarity);
		
		System.exit(0);
		String term = "";
		String general = "";
		
		for (int i = 0; i < train.size(); i++) {
			term = term + train.instance(i).stringValue(0);
			general = general + train.instance(i).stringValue(1);			
		}
//        System.out.println("Enter 1st word ");
//        String s1=scan.next();
//        System.out.println("Enter 2nd word ");
//        String s2=scan.next();

        jaccard_coeffecient("AAA","ABA");
        

    }

    private static void jaccard_coeffecient(String s1, String s2) {

        double j_coeffecient;
        ArrayList<String> j1 = new ArrayList<String>();
        ArrayList<String> j2 = new ArrayList<String>();
        HashSet<String> set1 = new HashSet<String>();
        HashSet<String> set2 = new HashSet<String>();
        
//            s1="$"+s1+"$";
//            s2="$"+s2+"$";
            int j=0;
            int i=1;
        
            while(i<=s1.length())
            {
                j1.add(s1.substring(j, i));
                    j++;
                    i++;
            }    
            j=0;
            i=1;
            while(i<=s2.length())
            {
                j2.add(s2.substring(j, i));
                    j++;
                    i++;
            }    

            
            Iterator<String> itr1 = j1.iterator();
            while (itr1.hasNext()) {
                  String element = itr1.next();
                  System.out.print(element + " ");
                }
                System.out.println();
                Iterator<String> itr2 = j2.iterator();
                while (itr2.hasNext()) {
                  String element = itr2.next();
                  System.out.print(element + " ");
                }
                System.out.println();
            
                
                set2.addAll(j2);
                set2.addAll(j1);
                set1.addAll(j1);
                set1.retainAll(j2);
                
                    
                System.out.println("Union="+set2.size());
                System.out.println("Intersection="+set1.size());
                
                j_coeffecient=((double)set1.size())/((double)set2.size());
                System.out.println("Jaccard coeffecient="+j_coeffecient);

    }
    

    }
