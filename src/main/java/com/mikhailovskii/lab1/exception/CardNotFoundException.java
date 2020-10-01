package com.mikhailovskii.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Card not found")
public class CardNotFoundException extends BaseException {

    public CardNotFoundException(Integer cardId) {
        super("Card with id " + cardId + " was not found");
    }

}
