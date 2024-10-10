package com.pro.sky.java.course2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends IllegalArgumentException {
    public QuestionNotFoundException() {
        super("Вопрос не найден");
    }
}
