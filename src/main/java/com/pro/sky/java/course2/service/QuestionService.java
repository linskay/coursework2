package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;

import java.util.Collection;
import java.util.Set;

public interface QuestionService {


    Set<Question> getQuestions(int limit) throws TooManyQuestionsException;

    void addQuestion(Question question);

    void removeQuestion(Question question);

    Collection<Question> getRandomQuestion(int count);
}