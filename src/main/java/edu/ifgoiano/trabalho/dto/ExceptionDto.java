package edu.ifgoiano.trabalho.dto;

import java.time.LocalDateTime;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

public class ExceptionDto extends RepresentationModel<ExceptionDto> {

  public final HttpStatus httpStatusMessage;
  public final Integer httpStatusCode;
  public final String message;
  public final String path;
  public final LocalDateTime timestamp;
  
  public ExceptionDto(HttpStatus httpStatusMessage, String message,
      String path) {
    this.httpStatusMessage = httpStatusMessage;
    this.httpStatusCode = httpStatusMessage.value();
    this.message = message;
    this.path = path;
    this.timestamp = LocalDateTime.now();
  }
  
  public ExceptionDto(HttpStatus httpStatusMessage, String message,
      String path, LocalDateTime timestamp) {
    this.httpStatusMessage = httpStatusMessage;
    this.httpStatusCode = httpStatusMessage.value();
    this.message = message;
    this.path = path;
    this.timestamp = timestamp;
  }

}
