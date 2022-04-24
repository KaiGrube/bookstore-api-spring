package org.grube.bookstoreapispring.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Configuration
@PropertySource("classpath:messages.yml")
public class ExceptionHandler {

    @Value("${idNotFound}") private String idNotFound;
    @Value("${methodArgumentTypeMismatchException}") private String methodArgumentTypeMismatchException;
    @Value("${globalException}") private String globalException;

    // todo: Custom ResourceNotFoundException mistakenly returns BAD REQUEST $ makes no sense at all :-)
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, idNotFound);
        apiException.addSubMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiException);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, methodArgumentTypeMismatchException);
        apiException.addSubMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiException);
    }

//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException exception, WebRequest request) {
//        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Number format exception");
//        apiException.addSubMessage(exception.getMessage());
//        return ResponseEntity.badRequest().body(apiException);
//    }

    // "global" exceptions (uncaught, default, fallthrough)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, globalException);
//        apiException.addSubMessage(exception.getMessage());
        apiException.addSubMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiException);
    }
}
