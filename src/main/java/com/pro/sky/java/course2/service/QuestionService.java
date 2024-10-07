package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    Set<Question> getQuestions(int limit) throws TooManyQuestionsException;

    void addQuestion(Question question);

    Question getRandomQuestion();

    void removeQuestion(Question question);
}