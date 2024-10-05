package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.exeption.TooManyQuestionsException;

import java.util.List;
import java.util.Set;

public interface QuestionService {
    Set<Question> getQuestions(int limit) throws TooManyQuestionsException;

    List<Question> getAllQuestions();
    Question getQuestionById(int id);
    void addQuestion(Question question);
    void deleteQuestion(int id);

    Question getRandomQuestion();

    void removeQuestion(Question question);
}