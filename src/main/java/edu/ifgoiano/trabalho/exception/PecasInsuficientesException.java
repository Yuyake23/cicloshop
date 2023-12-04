package edu.ifgoiano.trabalho.exception;

import java.io.Serial;

public class PecasInsuficientesException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public PecasInsuficientesException(String message) {
    super(message);
  }
  
}
