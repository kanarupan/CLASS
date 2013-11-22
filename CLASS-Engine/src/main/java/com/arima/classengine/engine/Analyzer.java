package com.arima.classengine.engine;

import java.util.ArrayList;
import java.util.List;

import com.arima.classengine.core.CAnalyzer;
import com.arima.classengine.profilematcher.NearestProfile;

public class Analyzer {

	public static void updateModel(int year, int grade, int term, String subject) throws Exception{
		CAnalyzer.updateModel(year, grade, term, subject);
	}

	public static Model getModel(int year, int grade, int term, String subject) throws Exception{
		return CAnalyzer.loadModelFromDatabase(year, grade, term, subject);
	}
	
	/**Usage Example
	 * 	
	 * 
	 * List<String> subjects = new ArrayList<String>();
		subjects.add("SAIVISM");
		subjects.add("MATHEMATICS");
		subjects.add("SCIENCE AND TECHNOLOGY");
		subjects.add("TAMIL LANGUAGE");
		subjects.add("ENGLISH LANGUAGE");
		subjects.add("HISTORY");
		subjects.add("INFORMATION AND COMMUNICATION TECHNOLOGY");
		subjects.add("BUSSINESS AND ACCOUNTING");
		
		ArrayList<Integer> marks = new ArrayList<Integer>();
		marks.add(90);
		marks.add(82);
		marks.add(96);
		marks.add(90);
		marks.add(82);
		marks.add(96);
		marks.add(82);
		marks.add(96);

		ArrayList<Integer> indexNumbers = getNearestProfiles(11089, 11, 3, subjects, marks);
		System.out.println(indexNumbers);
	 * 
	 * @param schoolNo
	 * @param grade
	 * @param term
	 * @param subjects
	 * @param marks
	 * @return
	 * @throws Exception
	 */

	//it will return nearest profiles from same school (ySchool DB index numbers)
	public static ArrayList<Integer> getNearestProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{	
		return NearestProfile.getNearestProfiles(schoolNo, grade, term, subjects, marks);
	}
	
	//it will return nearest profiles from all schools (central DB index numbers)
	public static ArrayList<Integer> getNearestProfiles(int grade, int term, List<String> subjects, List<Integer> marks) throws Exception{	
		return NearestProfile.getNearestProfiles(grade, term, subjects, marks);
	}
}
