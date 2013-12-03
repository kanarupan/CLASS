package com.arima.classengine.classifier;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.AdaBoostM1;
import weka.core.Instances;

/**
 * Created with IntelliJ IDEA.
 * User: Rajkumar
 * Date: 12/3/13
 * Time: 7:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class CBoostNaiveBayes implements CClassifier{

    public Classifier buildClassifier(Instances subject) throws Exception {

        if (subject.classIndex() == -1)
            subject.setClassIndex(subject.numAttributes() - 1);

        subject.deleteWithMissingClass();

        NaiveBayes nb = new NaiveBayes();

        AdaBoostM1 boost = new AdaBoostM1();

        boost.setClassifier(nb);
        boost.setNumIterations(100);
//		bag.setSeed(3);
        boost.buildClassifier(subject);

        return boost;

    }
}
