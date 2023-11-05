package com.eightaugusto.spring.data.jpa.criteria.example.common.handler;

import com.eightaugusto.spring.data.jpa.criteria.example.common.dto.ErrorDto;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * <code>ResponseEntityExceptionHandler</code> will handle the applications exceptions using the
 * default <code>ResponseEntityExceptionHandler</code> mechanism and responding with <code>ErrorDto
 * </code> format.
 */
@Log4j2
@ControllerAdvice
public class ResponseEntityExceptionHandler
    extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

  private static final String LOG_ERROR_MESSAGE =
      "Exception '{}' status '{}' with error '{}' and message '{}'";
  private static final String ERROR_FORMAT = "%s: '%s'";

  @Override
  protected ResponseEntity handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    final HttpStatus httpStatus =
        Optional.ofNullable(status)
            .filter(HttpStatusCode::is5xxServerError)
            .map(httpStatusCode -> HttpStatus.valueOf(httpStatusCode.value()))
            .orElse(HttpStatus.BAD_REQUEST);
    final Set<String> messages =
        ex.getBindingResult().getFieldErrors().stream()
            .map(
                fieldError ->
                    String.format(
                        ERROR_FORMAT, fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toSet());
    return getErrorResponseEntity(ex, httpStatus.value(), httpStatus.getReasonPhrase(), messages);
  }

  private ResponseEntity<ErrorDto> getErrorResponseEntity(
      Exception ex, int status, String error, Set<String> message) {
    log.error(LOG_ERROR_MESSAGE, ex.getClass(), status, error, message);
    return ResponseEntity.status(status).body(new ErrorDto(status, error, message));
  }
}
