package com.arima.classanalyzer.selector;

import weka.attributeSelection.*;
import weka.core.*;
import weka.core.converters.ConverterUtils.*;
import weka.classifiers.*;
import weka.classifiers.meta.*;
import weka.classifiers.trees.*;
import weka.filters.*;

import java.util.*;

import com.arima.classanalyzer.filter.CFilter;

public class AttributeSelector {

  /**
   * uses the meta-classifier
   */
  protected static void useClassifier(Instances data) throws Exception {
    System.out.println("\n1. Meta-classfier");
    AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
    CfsSubsetEval eval = new CfsSubsetEval();
    GreedyStepwise search = new GreedyStepwise();
    search.setSearchBackwards(true);
    J48 base = new J48();
    classifier.setClassifier(base);
    classifier.setEvaluator(eval);
    classifier.setSearch(search);
    Evaluation evaluation = new Evaluation(data);
    evaluation.crossValidateModel(classifier, data, 10, new Random(1));
    System.out.println(evaluation.toSummaryString());
  }

  /**
   * uses the filter
   */
  protected static void useFilter(Instances data) throws Exception {
    System.out.println("\n2. Filter");
    weka.filters.supervised.attribute.AttributeSelection filter = new weka.filters.supervised.attribute.AttributeSelection();
    CfsSubsetEval eval = new CfsSubsetEval();
    GreedyStepwise search = new GreedyStepwise();
    search.setSearchBackwards(true);
    filter.setEvaluator(eval);
    filter.setSearch(search);
    filter.setInputFormat(data);
    Instances newData = Filter.useFilter(data, filter);
    System.out.println(newData);
  }

  /**
   * uses the low level approach
   */
  protected static void useLowLevel(Instances data) throws Exception {
    System.out.println("\n3. Low-level");
    AttributeSelection attsel = new AttributeSelection();
    CfsSubsetEval eval = new CfsSubsetEval();
    GreedyStepwise search = new GreedyStepwise();
    search.setSearchBackwards(true);
    attsel.setEvaluator(eval);
    attsel.setSearch(search);
    attsel.SelectAttributes(data);
    int[] indices = attsel.selectedAttributes();
    System.out.println("selected attribute indices (starting with 0):\n" + Utils.arrayToString(indices));
  }

  /**
   * takes a dataset as first argument
   *
   * @param args        the commandline arguments
   * @throws Exception  if something goes wrong
   */
  public static void main(String[] args) throws Exception {
    // load data
    System.out.println("\n0. Loading data");
	String five = "1,2,3,4,5";
	String fives = "F,S,C,B,A";
	Instances train = CFilter.retrieveDatasetFromDatabase("select * from some", "root", "");
	train = CFilter.removeAttributesByNames(train, "index_no");
	train = CFilter.numeric2nominal(train, "first-last",5);
	train = CFilter.changeAttributesNominalValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", five);
	train = CFilter.renameAttributesValues(train, "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21", five, fives);

    if (train.classIndex() == -1)
    	train.setClassIndex(train.numAttributes() - 1);

    // 1. meta-classifier
//    useClassifier(train);

    // 2. filter
//    useFilter(train);

    // 3. low-level
    useLowLevel(train);
  }
}
