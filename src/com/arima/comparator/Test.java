package com.arima.comparator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JaccardCoefficient jc = new JaccardCoefficient();
		String[] s1 = {"A","A","C"};
		String[] s2 = {"A","A"};
		double similarity = jc.similarity(s1, s2);
		System.out.println(similarity);

		
	}

}
