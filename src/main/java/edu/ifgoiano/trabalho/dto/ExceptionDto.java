package edu.ifgoiano.trabalho.dto;

import java.time.LocalDateTime;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

public class ExceptionDto extends RepresentationModel<ExceptionDto> {

  public final String message;
  public final Integer httpStatusCode;
  public final HttpStatus httpStatusMessage;
  public final LocalDateTime timestamp;

  public ExceptionDto(String message, Integer httpStatusCode,
      HttpStatus httpStatusMessage, LocalDateTime timestamp) {
    this.message = message;
    this.httpStatusCode = httpStatusCode;
    this.httpStatusMessage = httpStatusMessage;
    this.timestamp = timestamp;
  }

}
