package edu.ifgoiano.trabalho.exception;

import java.io.Serial;

public class ContratoFuncionarioInvalidoException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public ContratoFuncionarioInvalidoException(String message) {
    super(message);
  }

}
