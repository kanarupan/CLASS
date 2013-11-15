package com.arima.classanalyzer.analyzer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.arima.classanalyzer.core.CAnalyzer;
import com.arima.classanalyzer.core.CFinal;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.Hello;
import com.arima.classanalyzer.webs.client.localhost._7070.class_wsdl.HelloClassService;
import com.mysql.jdbc.Connection;

public class Predictor {

	String url = "jdbc:mysql://localhost:3306/";
	String dbName = "class_analyzer";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "";


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**Predict next term performance from past performances
	 * @param conn
	 * @param grade
	 * @param term
	 * @param subject
	 * @param index_no
	 * @param marks
	 * @return
	 * @throws Exception
	 */
	public  String predictNextAsResults(Connection conn, int year, int grade,int term, String subject,  int index_no, ArrayList<Integer> marks) throws Exception{

		return CFinal.predictNextTermAsResults(conn, year, grade, term, subject, index_no, marks);
	}

	public  ArrayList<Integer> predictNext(Connection conn, int year, int grade,int term, String subject,  int index_no, ArrayList<Integer> marks) throws Exception{

		return CFinal.predictNextTerm(conn, year, grade, term, subject, index_no, marks);
	}
	

	/**Initiate connection to CLASS Database
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public  Connection connect(String url, String dbName, String username, String password){
		try {
			Class.forName(driver).newInstance();
			Connection conn = (Connection) DriverManager.getConnection(url+dbName,username,password);
			return conn;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public boolean close(Connection conn){
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


    public static void main(String[] args) {
        HelloClassService helloClassService=new HelloClassService();
        Hello hello= helloClassService.getHelloClassPort();
        System.out.println(hello.getmodel());
    }
}
