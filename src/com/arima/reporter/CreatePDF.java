package com.arima.reporter;
import java.io.*;
import java.sql.*;

import weka.core.Instances;
import weka.experiment.InstanceQuery;

import com.arima.filter.CFilter;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class CreatePDF{
    public static void main(String arg[])throws Exception{
    	
    	InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("");
		query.setQuery("select * from al_model");
		Instances train = query.retrieveInstances();
		train.setClassIndex(train.numAttributes() - 1);
		train = CFilter.numeric2nominal(train, "2-last");
		
       Document document=new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:/JSF/data1.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(train.numAttributes());
       
       for (int i = 0; i < train.numAttributes(); i++) {
		table.addCell(train.attribute(i).name());
	}
       
//       table.addCell("Indes Numbers");
//       table.addCell("Maths");
//       table.addCell("Physics");
//       table.addCell("Chemistry");
 
		for (int i = 0; i < train.numInstances(); i++) {
			
			for (int j = 0; j < train.numAttributes(); j++) {
				
//				System.out.print(train.attribute(j).name());
//				System.out.print(" : ");
//				System.out.print(train.instance(i).toString(j));
				table.addCell(train.instance(i).toString(j));
//				System.out.print(" ");
			}
//			System.out.println();
		}    
			
		
//		
//       Class.forName("com.mysql.jdbc.Driver");
//       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/class", "root", "");
//       Statement st=con.createStatement();
//       ResultSet rs=st.executeQuery("Select * from al_output");
//       while(rs.next()){
//       table.addCell(rs.getString("index_no"));
//       table.addCell(rs.getString("subject_1_final"));
//       table.addCell(rs.getString("subject_2_final"));
//       table.addCell(rs.getString("subject_3_final"));
//       }
       document.add(table);
       document.close();
   }
}