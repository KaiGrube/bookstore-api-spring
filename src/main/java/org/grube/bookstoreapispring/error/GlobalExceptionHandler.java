package org.grube.bookstoreapispring.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, "Resource not found.");
        apiException.addSubMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiException);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleNumberFormatException(MethodArgumentTypeMismatchException exception, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Number format exception");
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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        ApiException apiException = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "'Global' exception occurred");
//        apiException.addSubMessage(exception.getMessage());
        apiException.addSubMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiException);
    }
}
