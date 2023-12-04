package edu.ifgoiano.trabalho.exception;

import java.io.Serial;

public class ArquivoNaoEncontradoException extends RecursoNaoEncontradoException {

  @Serial
  private static final long serialVersionUID = 1L;

  public ArquivoNaoEncontradoException(String message) {
    super(message);
  }

}
