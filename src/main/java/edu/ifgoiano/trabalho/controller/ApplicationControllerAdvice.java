package edu.ifgoiano.trabalho.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import edu.ifgoiano.trabalho.dto.ExceptionDto;
import edu.ifgoiano.trabalho.exception.RecursoNaoEncontradoException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  @ResponseStatus(NOT_FOUND)
  public ExceptionDto handleRecursoNaoEncontradoException(RecursoNaoEncontradoException e, HttpServletRequest request) {
    ExceptionDto exceptionDto = new ExceptionDto(NOT_FOUND, e.getMessage(), request.getRequestURI());
    
    return exceptionDto;
  }
  
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(BAD_REQUEST)
  public ExceptionDto handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
    ExceptionDto exceptionDto = new ExceptionDto(BAD_REQUEST, e.getMessage(), request.getRequestURI());
    
    return exceptionDto;
  }
  
  @ExceptionHandler(SignatureException.class)
  @ResponseStatus(FORBIDDEN)
  public ExceptionDto handleSignatureException(SignatureException e, HttpServletRequest request) {
    ExceptionDto exceptionDto = new ExceptionDto(FORBIDDEN, e.getMessage(), request.getRequestURI());
    
    return exceptionDto;
  }
  
  @ExceptionHandler(ExpiredJwtException.class)
  @ResponseStatus(FORBIDDEN)
  public ExceptionDto handleExpiredJwtException(ExpiredJwtException e, HttpServletRequest request) {
    ExceptionDto exceptionDto = new ExceptionDto(FORBIDDEN, e.getMessage(), request.getRequestURI());
    
    return exceptionDto;
  }
  
}
