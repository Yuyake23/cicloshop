package edu.ifgoiano.trabalho.exception;

import java.io.Serial;

public class RecursoNaoEncontradoException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public RecursoNaoEncontradoException(String message) {
    super(message);
  }
}
