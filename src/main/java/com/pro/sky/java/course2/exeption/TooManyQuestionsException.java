package com.pro.sky.java.course2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TooManyQuestionsException extends RuntimeException {
    public TooManyQuestionsException() {
        super("Указано много вопросов. Максимальное количество - 5");
    }

    public TooManyQuestionsException(String message) {
        super(message);
    }
}