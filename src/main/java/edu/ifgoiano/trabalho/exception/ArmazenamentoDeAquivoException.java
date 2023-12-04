package edu.ifgoiano.trabalho.exception;

import java.io.Serial;

public class ArmazenamentoDeAquivoException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public ArmazenamentoDeAquivoException(String message) {
    super(message);
  }
  
}
