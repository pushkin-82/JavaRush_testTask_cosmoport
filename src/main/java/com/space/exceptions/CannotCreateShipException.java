package com.space.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotCreateShipException extends RuntimeException {

    public CannotCreateShipException(String message) {
        super(message);
    }
}
