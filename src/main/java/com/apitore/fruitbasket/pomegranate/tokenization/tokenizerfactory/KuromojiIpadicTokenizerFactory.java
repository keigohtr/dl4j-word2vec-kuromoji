package com.apitore.fruitbasket.pomegranate.tokenization.tokenizerfactory;


import java.io.InputStream;

import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import com.apitore.fruitbasket.pomegranate.tokenization.tokenizer.KuromojiIpadicTokenizer;


public class KuromojiIpadicTokenizerFactory implements TokenizerFactory {

  private TokenPreProcess preProcess;


  @Override
  public Tokenizer create(String toTokenize) {
    if (toTokenize == null || toTokenize.isEmpty()) {
      throw new IllegalArgumentException("Unable to proceed; no sentence to tokenize");
    }

    KuromojiIpadicTokenizer ret = new KuromojiIpadicTokenizer(toTokenize);
    ret.setTokenPreProcessor(preProcess);
    return ret;
  }

  @Override
  public Tokenizer create(InputStream paramInputStream) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTokenPreProcessor(TokenPreProcess preProcess) {
    this.preProcess = preProcess;
  }

  @Override
  public TokenPreProcess getTokenPreProcessor() {
    return this.preProcess;
  }

}
