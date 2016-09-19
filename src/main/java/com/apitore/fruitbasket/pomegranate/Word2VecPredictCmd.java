package com.apitore.fruitbasket.pomegranate;


import java.io.File;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;


public class Word2VecPredictCmd {

  public static void main (final String[] args) throws Exception {
    WordVectors vec = WordVectorSerializer.loadTxtVectors(new File(args[0]));

    Collection<String> lst = vec.wordsNearest("day", 10);
    System.out.println(lst);
    double cosSim = vec.similarity("day", "night");
    System.out.println(cosSim);
    double[] wordVector = vec.getWordVector("day");
    System.out.println(wordVector);
  }

}
