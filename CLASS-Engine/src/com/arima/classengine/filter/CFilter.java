package com.arima.classengine.filter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.ProtectedProperties;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.DatabaseSaver;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.Add;
import weka.filters.unsupervised.attribute.AddValues;
import weka.filters.unsupervised.attribute.MathExpression;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.Standardize;

import com.arima.classengine.utils.Utils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.StringUtils;

public class CFilter {

	public static Instances createInstances(int index, ArrayList<Integer> marks){
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		
		Attribute index_no = new Attribute("index_no");
		attributes.add(index_no);
		
		int terms = marks.size();

		if(terms == 1){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
		}else if(terms == 2){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
			Attribute subject_1_term_2 = new Attribute("subject_1_term_2");
			attributes.add(subject_1_term_2);
		}else if(terms == 3){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
			Attribute subject_1_term_2 = new Attribute("subject_1_term_2");
			attributes.add(subject_1_term_2);
			Attribute subject_1_term_3 = new Attribute("subject_1_term_3");
			attributes.add(subject_1_term_3);
		}else if(terms == 4){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
			Attribute subject_1_term_2 = new Attribute("subject_1_term_2");
			attributes.add(subject_1_term_2);
			Attribute subject_1_term_3 = new Attribute("subject_1_term_3");
			attributes.add(subject_1_term_3);
			Attribute subject_1_term_4 = new Attribute("subject_1_term_4");
			attributes.add(subject_1_term_4);
		}else if(terms == 5){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
			Attribute subject_1_term_2 = new Attribute("subject_1_term_2");
			attributes.add(subject_1_term_2);
			Attribute subject_1_term_3 = new Attribute("subject_1_term_3");
			attributes.add(subject_1_term_3);
			Attribute subject_1_term_4 = new Attribute("subject_1_term_4");
			attributes.add(subject_1_term_4);
			Attribute subject_1_term_5 = new Attribute("subject_1_term_5");
			attributes.add(subject_1_term_5);
		}else if(terms == 6){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
			Attribute subject_1_term_2 = new Attribute("subject_1_term_2");
			attributes.add(subject_1_term_2);
			Attribute subject_1_term_3 = new Attribute("subject_1_term_3");
			attributes.add(subject_1_term_3);
			Attribute subject_1_term_4 = new Attribute("subject_1_term_4");
			attributes.add(subject_1_term_4);
			Attribute subject_1_term_5 = new Attribute("subject_1_term_5");
			attributes.add(subject_1_term_5);
			Attribute subject_1_term_6 = new Attribute("subject_1_term_6");
			attributes.add(subject_1_term_6);
		}else if(terms == 7){
			Attribute subject_1_term_1 = new Attribute("subject_1_term_1");
			attributes.add(subject_1_term_1);
			Attribute subject_1_term_2 = new Attribute("subject_1_term_2");
			attributes.add(subject_1_term_2);
			Attribute subject_1_term_3 = new Attribute("subject_1_term_3");
			attributes.add(subject_1_term_3);
			Attribute subject_1_term_4 = new Attribute("subject_1_term_4");
			attributes.add(subject_1_term_4);
			Attribute subject_1_term_5 = new Attribute("subject_1_term_5");
			attributes.add(subject_1_term_5);
			Attribute subject_1_term_6 = new Attribute("subject_1_term_6");
			attributes.add(subject_1_term_6);
			Attribute subject_1_term_7 = new Attribute("subject_1_term_7");
			attributes.add(subject_1_term_7);
		}
		
		Instances dataset = new Instances("Test-dataset", attributes, 0);
		
		Instance inst = new DenseInstance(marks.size()+1); 
		inst.setValue(0, index); 

		for (int i = 0; i < marks.size(); i++) {
			inst.setValue(i+1, marks.get(i));
			System.out.println(marks.get(i));
		}
		inst.setDataset(dataset);
		dataset.add(inst);
		
		System.out.println("The instance: " + dataset); 
		return dataset;
	}

	public static void arff2Database(Instances data, String table_name,String URL, String username, String password)
			throws Exception {

		DatabaseSaver save = new DatabaseSaver();
		save.setUrl(URL);
		save.setUser(username);
		save.setPassword(password);
		save.setInstances(data);
		save.setRelationForTableName(false);
		save.setTableName(table_name);
		save.connectToDatabase();
		save.setTruncate(true);
		save.writeBatch();
	}

	public static Instances removeAttributesByIndices(Instances inst, String indices) throws Exception {

		Instances instNew;
		Remove remove;

		remove = new Remove();
		remove.setAttributeIndices(indices);
		remove.setInputFormat(inst);
		instNew = Filter.useFilter(inst, remove);

		return instNew;

	}

	public static Instances removeAttributesByNames(Instances inst, String attributes) throws Exception {

		String indices = attributes2Indices(inst, attributes);
		if(indices != null){
			return removeAttributesByIndices(inst, indices);
		}else{
			System.out.println("Error in your attribute names");
			return null;
		}

	}

	public static String attributes2Indices(Instances inst, String attributes) {

		if (!StringUtils.isEmptyOrWhitespaceOnly(attributes)) {

			String arr[] = attributes.split(",");
			String index_numbers = "";

			for (String attribute : arr) {

				if (inst.attribute(attribute) != null) {

					index_numbers += inst.attribute(attribute).index() + 1
							+ ",";
				} else {

					System.out.println("Please Give correct indices!");
					return null;
				}

			}

			index_numbers = index_numbers.substring(0, index_numbers.length() - 1);
			//			System.out.println(index_numbers);
			return index_numbers;

		} else {
			System.out.println("Please Give some indices!");
			return null;
		}

	}


	public static Instances retrieveDatasetFromDatabase(String theQuery, String username, String password)
			throws Exception {

		InstanceQuery query = new InstanceQuery();
		query.setUsername(username);
		query.setPassword(password);
		query.setQuery(theQuery);
		Instances train = query.retrieveInstances();

		return train;
	}

	public static Instances numeric2nominalAuto(Instances data, String indices)
			throws Exception {

		/*
		 * 5 - A (75<marks) 4 - B (65<marks<75) 3 - C (50<marks<65) 2 - S
		 * (35<marks<50) 1 - F (marks<35)
		 */

		//		MathExpression normFilter = new MathExpression();
		//		normFilter.setIgnoreRange(indices); // Set which attributes are to be ignored
		//		normFilter.setInvertSelection(true);
		//		normFilter.setExpression("ifelse(A>35, ifelse(A>50, ifelse(A>65, ifelse(A>75, 5, 4), 3), 2), 1)");
		//		normFilter.setInputFormat(data);
		//		Instances normalizedData = Filter.useFilter(data, normFilter);

		Discretize filter = new Discretize();
		filter.setInputFormat(data);
		//		     filter.setAttributeIndices(indices);
		//		     filter.setAttributeIndices(indices);
		filter.desiredWeightOfInstancesPerIntervalTipText();
		filter.setBins(5);
		filter.setUseBinNumbers(true);
		System.out.println(filter.getUseBinNumbers());
		//		     System.out.println(filter.getBinRangesString(0));
		//		     System.out.println(filter.);

		data = Filter.useFilter(data, filter);
		System.out.println(filter.getBinRangesString(0));

		return data;
	}


	public static Instances numeric2nominal(Instances data, String indices, int bins)
			throws Exception {

		/*
		 * 5 - A (75<marks) 4 - B (65<marks<75) 3 - C (50<marks<65) 2 - S
		 * (35<marks<50) 1 - F (marks<35)
		 */
		String expression;
		switch (bins) {
		case 2:  expression = "ifelse(A>35, 2, 1)";
		break;
		case 3:  expression = "ifelse(A>35, ifelse(A>55, 3, 2), 1)";
		break;
		case 4:  expression = "ifelse(A>35, ifelse(A>55, ifelse(A>65, 4, 3), 2), 1)";
		break;
		case 5:  expression = "ifelse(A>35, ifelse(A>55, ifelse(A>65, ifelse(A>75, 5, 4), 3), 2), 1)";
		break;
		default: expression = "ifelse(A>35, ifelse(A>55, ifelse(A>65, ifelse(A>75, 5, 4), 3), 2), 1)";
		break;
		}

		MathExpression normFilter = new MathExpression();
		normFilter.setIgnoreRange(indices); // Set which attributes are to be ignored
		normFilter.setInvertSelection(true);
		normFilter.setExpression(expression);
		normFilter.setInputFormat(data);
		Instances normalizedData = Filter.useFilter(data, normFilter);

		NumericToNominal nTon = new NumericToNominal();
		// nTon.setAttributeIndices(indices);
		// nTon.setInvertSelection(true);
		nTon.setInputFormat(normalizedData);
		normalizedData = Filter.useFilter(normalizedData, nTon);

		return normalizedData;
	}

	public static Instances addAttributes(Instances dataset, String attribute_name, String attribute_type,
			String attribute_position, String nominal_lables) throws Exception {

		Add filter;
		Instances newData = new Instances(dataset);

		filter = new Add();
		filter.setAttributeIndex(attribute_position);
		if (attribute_type == "nominal") {
			filter.setNominalLabels(nominal_lables);
		}
		filter.setAttributeName(attribute_name);
		filter.setInputFormat(newData);

		return newData = Filter.useFilter(newData, filter);

	}

	public static Instances changeAttributeNominalValues(Instances inst, int i, String nominalValues) throws Exception{

		AddValues filter;
		Instances newData = new Instances(inst);

		filter = new AddValues();
		filter.setAttributeIndex(""+i);
		filter.setLabels(nominalValues);
		filter.setSort(true);
		filter.setInputFormat(newData);

		return newData = Filter.useFilter(newData, filter);

	}

	public static Instances changeAttributesNominalValues(Instances inst, String indices, String nominalValues) throws Exception{

		ArrayList<Integer> intList = String2Ints(indices);
		int index;

		for (int i = 0; i < intList.size(); i++) {
			index = intList.get(i);
			inst = changeAttributeNominalValues(inst, index, nominalValues);
		}

		return inst;
	}

	public static void saveARFF(Instances instances,String path) {

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(
					new FileWriter(path));
			writer.write(instances.toString());
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static Instances loadARFF(){

		ArffLoader loader = new ArffLoader();
		try {
			loader.setFile(new File("/some/where/data.arff"));
			Instances data = loader.getStructure();

			return data;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static void saveCSV(Instances instances, String path) {

		CSVSaver saver = new CSVSaver();
		saver.setInstances(instances);
		try {
			saver.setFile(new File(path));
			saver.writeBatch();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Instances loadCSV(String path){

		Instances data;
		try {
			data = DataSource.read(path);
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	


	}

	public static void saveModel(Classifier cls, String path) {

		try {
			weka.core.SerializationHelper.write(path, cls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Classifier loadModel(String path) {

		try {
			Classifier cls = (Classifier) weka.core.SerializationHelper.read(path);
			return cls;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	public static ArrayList<Integer> String2Ints(String str){

		ArrayList<Integer> intList = new ArrayList<Integer>(); 

		if (!StringUtils.isEmptyOrWhitespaceOnly(str)) {

			String[] tokens = str.split(",");


			for (int i = 0; i < tokens.length; i++) {
				intList.add(Integer.parseInt(tokens[i]));
			}

		} else {
			System.out.println("Please Give some indices!");
		}

		return intList;
	}

	public static Instances renameAttributesValues(Instances inst, String indices, String oldValues, String newValues){

		Instances newData = new Instances(inst);
		String[] indexTokens = indices.split(",");
		String[] oldTokens = oldValues.split(",");
		String[] newTokens = newValues.split(",");

		for (int j = 0; j < indexTokens.length; j++) {
			for (int i = 0; i < newTokens.length; i++) {
				newData.renameAttributeValue(Integer.parseInt(indexTokens[j])-1, Integer.parseInt(oldTokens[i])-1, newTokens[i]);
			}			
		}
		return newData;
	}
	
    public static boolean appendfile(String path,String textToAppend) {
        try {


            File file = new File(path);

            //if file doesnt exists, then create it
            if (!file.exists()) {
                 file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file.getCanonicalFile(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(textToAppend+"\n");
            bufferWritter.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public static void main(String[] args) throws Exception{
    	
    	Instances train = Utils.prepareStandardizedAndNormalizedTrainDataAcrossSchools(10, 2, "MATHEMATICS");

    	System.out.println(train);
    }
    
    public static Instances standardize(Instances train) throws Exception{
    	
    	train.setClassIndex(0);
    	
    	Standardize std = new Standardize();
    	std.setInputFormat(train);
    	train = Filter.useFilter(train, std);
    	
    	train.setClassIndex(-1);
    	
    	return train;
    }
    
    public static Instances normalize(Instances train) throws Exception{
    	
    	train.setClassIndex(0);
    	
    	Normalize norm = new Normalize();
    	norm.setInputFormat(train);
    	norm.setScale(100);
    	train = Filter.useFilter(train, norm);
    	
    	train.setClassIndex(-1);
    	
    	return train;
    }
    
public static Instances createInstance(int index, ArrayList<Integer> marks){
		
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		
		Attribute index_no = new Attribute("index_no");
		attributes.add(index_no);
		
		Attribute temp;
		
		for (int i = 0; i < marks.size(); i++) {
			
			temp = new Attribute("attribute_"+(i+1));
			attributes.add(temp);
		}
		
		Instances dataset = new Instances("Test-dataset", attributes, 0);
		
		Instance inst = new DenseInstance(marks.size()+1); 
		inst.setValue(0, index); 

		for (int i = 0; i < marks.size(); i++) {
			inst.setValue(i+1, marks.get(i));
		}
		inst.setDataset(dataset);
		dataset.add(inst);
		
		return dataset;
	}
    
}
