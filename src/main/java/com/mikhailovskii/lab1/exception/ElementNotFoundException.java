package com.mikhailovskii.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends BaseException {

    public ElementNotFoundException(Integer elementId) {
        super("Element with id " + elementId + " was not found");
    }

}
