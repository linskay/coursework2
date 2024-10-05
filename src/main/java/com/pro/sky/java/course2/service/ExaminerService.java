package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.exeption.TooManyQuestionsException;

import java.util.List;
import java.util.Set;

public interface ExaminerService {

    List<Question> getQuestions(int count);

    Set<Question> getQuestionsToAsk(int amount) throws TooManyQuestionsException;
}
