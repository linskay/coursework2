package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;

import java.util.List;

public interface ExaminerService {

    List<Question> getQuestions(int amount) throws TooManyQuestionsException;
}