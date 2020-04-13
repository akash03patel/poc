package com.fincity.carpoc.exception;

import java.time.LocalDateTime;
import com.fincity.carpoc.model.CustomErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);

  @ExceptionHandler(CarNotFoundException.class)
  public ResponseEntity<CustomErrorResponse> customCarNotFoundExceptionHandler(Exception ex, WebRequest request) {

    LOGGER.error("Exception:  {} in the path:{} ", ex.getMessage(), request.getContextPath());

    final CustomErrorResponse errors = CustomErrorResponse.builder()
        .status(HttpStatus.NOT_FOUND.value())
        .error(ex.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CarServiceException.class)
  public ResponseEntity<CustomErrorResponse> customCarServiceHandler(Exception ex, WebRequest request) {

    LOGGER.error("Exception:  {} in the path:{} ", ex.getMessage(), request.getContextPath());

    final CustomErrorResponse errors = CustomErrorResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error(ex.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<CustomErrorResponse> customExceptionHandler(Exception ex, WebRequest request) {
    LOGGER.error("Exception:  {} in the path:{} ", ex.getMessage(), request.getContextPath());
    final CustomErrorResponse errors = CustomErrorResponse.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .error(ex.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();
    return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
