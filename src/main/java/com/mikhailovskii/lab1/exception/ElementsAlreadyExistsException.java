package com.mikhailovskii.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ElementsAlreadyExistsException extends BaseException {

    public ElementsAlreadyExistsException(Integer elementId) {
        super("Element with format " + elementId + " already exists.");
    }

}
