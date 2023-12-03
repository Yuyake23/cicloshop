package edu.ifgoiano.trabalho.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public RecursoNaoEncontradoException(String message) {
    super(message);
  }
}
