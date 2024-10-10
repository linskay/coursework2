package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}