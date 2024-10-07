package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public List<Question> getQuestions(int amount, String question, String answer)
            throws TooManyQuestionsException {
        if (amount > 5) {
            throw new TooManyQuestionsException("Указано много вопросов. Максимальное количество - 5");
        }
        Question newQuestion = new Question(question, answer);
        javaQuestionService.addQuestion(newQuestion);
        Set<Question> questions = javaQuestionService.getQuestions(amount);

        if (questions.size() < amount) {
            throw new TooManyQuestionsException("Недостаточно вопросов в базе. Запрошено: " + amount + ", доступно: " + questions.size());
        }
        return new ArrayList<>(questions);
    }
}