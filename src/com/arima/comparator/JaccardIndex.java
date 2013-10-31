package com.arima.comparator;

import java.util.Arrays;

import weka.core.Instance;
import weka.core.InstanceComparator;
import weka.core.Instances;
import weka.gui.streams.InstanceJoiner;

import com.arima.filter.CFilter;


class JaccardIndex {

    
    public static void main(String[] args) throws Exception {

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
		
//		Instances ok = Instances.mergeInstances(term, general);
//		System.out.println(ok);System.exit(0);
		
		JaccardIndex.getSimilarity(general,term);
		
    }
    
    public static float getSimilarity(Instances general, Instances term){
    	
		int[] term_nominalCounts = term.attributeStats(0).nominalCounts;
		int[] general_nominalCounts = general.attributeStats(0).nominalCounts;
		System.out.println(Arrays.toString(term_nominalCounts));
		System.out.println(Arrays.toString(general_nominalCounts));
	
		int F = Math.min(term_nominalCounts[0], general_nominalCounts[0]);
		int S = Math.min(term_nominalCounts[1], general_nominalCounts[1]);
		int C = Math.min(term_nominalCounts[2], general_nominalCounts[2]);
		int B = Math.min(term_nominalCounts[3], general_nominalCounts[3]);
		int A = Math.min(term_nominalCounts[4], general_nominalCounts[4]);
		
		System.out.println(F + " " + S + " " + C + " " + B + " " + A );
		float similiarity = (float)100 * (F + S + C + B + A)/term.attributeStats(0).totalCount;
		System.out.println("Similarity is : " + similiarity);
		
		return similiarity;
    }
    
    public static Instances validate(Instances data1, Instances data2){    	
    	Instances train1 = new Instances(data1);
    	Instances train2 = new Instances(data2);
    	Instances train;
    	InstanceComparator comp = new InstanceComparator();
    	
    	train1.sort(0);
    	train2.sort(0);
    	
		for (int i = 0; i < train1.size(); i++) {
			String x = train1.instance(i).attribute(0).value(i);
			String y = train2.instance(i).attribute(0).value(i);
			System.out.println(x.equalsIgnoreCase(y));
		} 

//		
//		int[] answer = new int[a.length + b.length];
//	    int i = 0, j = 0, k = 0;
//	    
//		   while (i < train1.size() && j < train2.size())
//		    {
//		        if (a[i] < b[j])
//		        {
//		            answer[k] = a[i];
//		            i++;
//		        }
//		        else
//		        {
//		            answer[k] = b[j];
//		            j++;
//		        }
//		        k++;
//		    }
//
//		    while (i < a.length)
//		    {
//		        answer[k] = a[i];
//		        i++;
//		        k++;
//		    }
//
//		    while (j < b.length)
//		    {
//		        answer[k] = b[j];
//		        j++;
//		        k++;
//		    }
//
//		    return answer;
    	
//    	System.out.println(data1);
//    	System.out.println(data2);
    	return data1;
    }
}
