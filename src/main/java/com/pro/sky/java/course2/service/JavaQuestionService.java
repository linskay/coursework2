package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.IncorrectParameterException;
import com.pro.sky.java.course2.exeption.QuestionNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    @PostConstruct
    void setUp() {
        questions.add(new Question("вопрос1", "ответ1"));
        questions.add(new Question("вопрос2", "ответ2"));
        questions.add(new Question("вопрос3", "ответ3"));
        questions.add(new Question("вопрос4", "ответ4"));
        questions.add(new Question("вопрос5", "ответ5"));
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new IncorrectParameterException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
        return q;
    }

    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new IncorrectParameterException();
        }
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return null;
    }

    @Override
    public Question remove(String question, String answer) {
        Question rem = new Question(question, answer);
        if (!questions.contains(rem)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(rem);
        return null;
    }

    @Override
    public Question getRandom() {
        Random random = new Random();
        Question[] questionsArray = questions.toArray(new Question[0]);
        int randomIndex = random.nextInt(questionsArray.length);
        return questionsArray[randomIndex];
    }

    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(questions);
    }
}
