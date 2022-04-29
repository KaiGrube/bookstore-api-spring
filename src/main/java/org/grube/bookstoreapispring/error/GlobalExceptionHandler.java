package org.grube.bookstoreapispring.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
@Configuration
@PropertySource("classpath:exceptions.yml")
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${ResourceNotFoundException}")
    private String resourceNotFoundExceptionMessage;
    @Value("${MethodArgumentTypeMismatchException}")
    private String methodArgumentTypeMismatchException;
    @Value("${GlobalException}")
    private String globalException;


    // todo: MissingServletRequestParameterException
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception, WebRequest request) {
        return ResponseEntity.badRequest().body(List.of(exception.getMessage()));
    }
    // todo: Custom MissingServletRequestParameterException mistakenly returns BAD REQUEST $ makes no sense at all :-)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
//        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, resourceNotFoundExceptionMessage);
//        apiException.addSubMessage(exception.getMessage());
        return ResponseEntity.notFound().build();
    }

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<Object> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {
//        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, methodArgumentTypeMismatchException);
//        apiException.addSubMessage(exception.getMessage());
//        return ResponseEntity.badRequest().body(apiException);
//    }

//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException exception, WebRequest request) {
//        ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Number format exception");
//        apiException.addSubMessage(exception.getMessage());
//        return ResponseEntity.badRequest().body(apiException);
//    }

    // "global" exceptions (uncaught, default, fallthrough)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        logger.error("UUUUUUUUUUUUPPPPPPS" + exception.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("UUUUUUUUUUUUPPPPPPS");
    }
}
