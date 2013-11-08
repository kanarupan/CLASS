package com.arima.classengine.engine;

import com.arima.classengine.core.CAnalyzer;

public class Analyzer {

	public void updateModel(int year, int grade, int term, String subject) throws Exception{
		CAnalyzer.updateModel(year, grade, term, subject);
	}

	public Model getModel(int year, int grade, int term, String subject) throws Exception{
		return CAnalyzer.loadModelFromDatabase(year, grade, term, subject);
	}

}
