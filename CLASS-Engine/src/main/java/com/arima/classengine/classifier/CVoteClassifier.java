package com.arima.classengine.classifier;

import com.arima.classengine.filter.CFilter;
import com.arima.classengine.utils.Utils;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.Vote;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SelectedTag;

public class CVoteClassifier implements CClassifier {

    public static void main(String[] args) throws Exception {
        Instances train = Utils.prepareTrainData(11089, 11, 3, "SAIVISM");
        train = CFilter.removeAttributesByNames(train, "idstudent");
        train = CFilter.numeric2nominal(train, "first-last",3);
        //Changing nominal lables so that every attribute will have the same
        train = Utils.changeAttributeNominalRange(train, Utils.getAttributeLables(3, true));
        //Renaming attribute values to different lables such as A,B,C,S,F
        train = Utils.renameAttributes(train, 3);
        CVoteClassifier vc = new CVoteClassifier();
        Classifier vote =   vc.buildClassifier(train);
        System.out.println(vote.getClass().getName());
//        System.exit(0);
    }

	public Classifier buildClassifier(Instances subject) throws Exception {
		
		if (subject.classIndex() == -1)
			subject.setClassIndex(subject.numAttributes() - 1);
		
		subject.deleteWithMissingClass();

		J48 j48 = new J48();
		j48.setUnpruned(true);
		j48.buildClassifier(subject);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(subject);

        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.buildClassifier(subject);
		
		Vote vote = new Vote();
		vote.setClassifiers(new Classifier[]{j48, nb, mlp});
		
		return vote;
	}

}
