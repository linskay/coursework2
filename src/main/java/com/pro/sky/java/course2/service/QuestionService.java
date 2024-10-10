package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(Question question);

    Question add(String question, String answer);

    Question remove(Question question);

    Question remove(String question, String answer);

    Question getRandom();

    Collection<Question> getAll();
}