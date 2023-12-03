package edu.ifgoiano.trabalho.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SenhaInvalidaException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1L;

  public SenhaInvalidaException(String mensagem) {
    super(mensagem);
  }
}
