package edu.ifgoiano.trabalho.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.time.LocalDateTime;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import edu.ifgoiano.trabalho.dto.ExceptionDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  @ResponseStatus(NOT_FOUND)
  public ExceptionDto handleRecursoNaoEncontradoException(RecursoNaoEncontradoException e) {
    ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), NOT_FOUND.value(), NOT_FOUND, LocalDateTime.now());
    
    return exceptionDto;
  }
  
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(BAD_REQUEST)
  public ExceptionDto handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), BAD_REQUEST.value(), BAD_REQUEST, LocalDateTime.now());
    
    return exceptionDto;
  }
  
  @ExceptionHandler(SignatureException.class)
  @ResponseStatus(FORBIDDEN)
  public ExceptionDto handleDataIntegrityViolationException(SignatureException e) {
    ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), FORBIDDEN.value(), FORBIDDEN, LocalDateTime.now());
    
    return exceptionDto;
  }
  
  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(FORBIDDEN)
  public ExceptionDto handleDataIntegrityViolationException(ExpiredJwtException e) {
    ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), FORBIDDEN.value(), FORBIDDEN, LocalDateTime.now());
    
    return exceptionDto;
  }
  
}
