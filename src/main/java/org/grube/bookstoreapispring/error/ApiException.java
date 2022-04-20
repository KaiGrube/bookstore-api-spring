package org.grube.bookstoreapispring.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiException {
    private final LocalDateTime timestamp;
    private final HttpStatus httpStatus;
    private final String message;
    private final List<String> subMessages;
    public ApiException(HttpStatus httpStatus, String message) {
        this.timestamp = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.subMessages = new ArrayList<>();
    }
    public void addSubMessage(String message) {
        subMessages.add(message);
    }
}

