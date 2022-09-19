package com.src.projectmanagementtoolservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdDuplicateException extends RuntimeException{

    public IdDuplicateException(String message) {
        super(message);
    }
}
