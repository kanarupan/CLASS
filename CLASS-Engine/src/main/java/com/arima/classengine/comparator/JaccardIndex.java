package com.arima.classengine.comparator;

import java.util.Arrays;

import weka.core.Instance;
import weka.core.InstanceComparator;
import weka.core.Instances;
import weka.gui.streams.InstanceJoiner;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;


public class JaccardIndex {
    
    public static void main(String[] args) throws Exception {
    			getJaccardIndexSimilarity(11089, 2008, 11, "HISTORY");
    }
    
    public static double getJaccardIndexSimilarity(int schoolNo, int year, int grade, String subject) throws Exception{

        int grade1 = grade;
        int term1 = 3;
        int grade2 = 11;
        int term2 = 2;

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
		
		Instances general = new Instances(train);
		train.deleteAttributeAt(1);
		general.deleteAttributeAt(0);
		
		return JaccardIndex.getSimilarity(general,train);
		
    }
    
    private static double getSimilarity(Instances general, Instances term){
    	
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
		double similiarity = (double)100 * (F + S + C + B + A)/term.attributeStats(0).totalCount;
		System.out.println("Similarity is : " + similiarity);
		
		return similiarity;
    }
    
    private static Instances validate(Instances data1, Instances data2){
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
    	return data1;
    }
}
