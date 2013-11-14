package com.arima.classengine.profilematcher;

import weka.core.Instances;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;


public class test {
	
	public static void main(String[] args) throws Exception{
		Instances inst = CFilter.retrieveDatasetFromDatabase(Utils.createPredictionQuery(11, 1, "MATHEMATICS"), "root", "");
	}
	
}
