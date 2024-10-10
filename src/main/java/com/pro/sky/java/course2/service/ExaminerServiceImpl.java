package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.IncorrectParameterException;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount ||
                amount <= 0) {
            throw new IncorrectParameterException();
        }
        Set<Question> resultQuestionSet = new HashSet<>();
        if (amount == questionService.getAll().size()) {
            return questionService.getAll();
        }
        while (amount > resultQuestionSet.size()) {
            resultQuestionSet.add(questionService.getRandom());
        }
        return resultQuestionSet;
    }
}
