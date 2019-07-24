package am.gitc.trello.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status,
                                                                WebRequest request) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", new Date());
    body.put("status", status.value());

    Map<String, String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream().collect(Collectors.toMap(FieldError::getField,
            FieldError::getDefaultMessage));
    body.put("errors", errors);

    return new ResponseEntity<>(body, headers, status);
  }

  @ExceptionHandler(value = Throwable.class)
  protected ResponseEntity<Object> handleThrowable(Throwable ex) {
    LOGGER.error("ERROR:", ex);
    return new ResponseEntity<>("Internal Server Error!", HttpStatus.INTERNAL_SERVER_ERROR);
  }

//  @ExceptionHandler()
//  protected ResponseEntity<Object> handleConflictException(Throwable ex) {
//    logger.error("ERROR:", ex);
//    return new ResponseEntity<>(new HashMap<String, String>(){{
//      put("error", ex.getMessage());
//    }}, HttpStatus.CONFLICT);
//  }


  private Object createBody(Throwable obj) {
    Map<String, Object> body = new HashMap<>();
    body.put("message", obj.getMessage());
    return body;
  }
}
