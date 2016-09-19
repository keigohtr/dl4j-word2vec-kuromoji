package com.apitore.fruitbasket.pomegranate;


import java.io.File;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.EndingPreProcessor;

import com.apitore.fruitbasket.pomegranate.tokenization.tokenizerfactory.KuromojiNeologdTokenizerFactory;


public class Word2VecCmd {

  public static void main (final String[] args) throws Exception {
    SentenceIterator iter = new BasicLineIterator(new File(args[0]));

    final EndingPreProcessor preProcessor    = new EndingPreProcessor();
    KuromojiNeologdTokenizerFactory tokenizer = new KuromojiNeologdTokenizerFactory();
    tokenizer.setTokenPreProcessor( new TokenPreProcess()
    {
      @Override
      public String preProcess( String token )
      {
        token       = token.toLowerCase();
        String base = preProcessor.preProcess( token );
        base        = base.replaceAll( "\\d" , "__NUMBER__" );
        return base;
      }
    });


    System.out.println( "Build model..." );
    int batchSize   = 1000;
    int iterations  = 5;
    int layerSize   = 150;

    Word2Vec vec = new Word2Vec.Builder()
        .batchSize(batchSize)
        .minWordFrequency(5)
        .useAdaGrad(false)
        .layerSize(layerSize)
        .iterations(iterations)
        .seed(7485)
        .windowSize(5)
        .learningRate(0.025)
        .minLearningRate(1e-3)
        .negativeSample(10)
        .iterate(iter)
        .tokenizerFactory(tokenizer)
        .workers(6)
        .build();
    vec.fit();
    WordVectorSerializer.writeWordVectors(vec, args[0]+".wordvectors.txt");
  }

}
