package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    @Override
    public Set<Question> getQuestions(int limit) throws TooManyQuestionsException {
        if (limit > 5) {
            throw new TooManyQuestionsException("Указано много вопросов. Максимальное количество - 5");
        }

        if (questions.size() <= limit) {
            return new HashSet<>(questions);
        } else {
            Set<Question> result = new HashSet<>();
            Random random = new Random();
            while (result.size() < limit) {
                int index = random.nextInt(questions.size());
                questions.stream().skip(index).findFirst().ifPresent(result::add);
            }
            return result;
        }
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    @Override
    public Collection<Question> getRandomQuestion(int count) {
        if (count <= 0 || count > questions.size()) {
            return new HashSet<>();
        }
        Set<Question> randomQuestions = new HashSet<>();
        Random random = new Random();
        while (randomQuestions.size() < count) {
            int randomIndex = random.nextInt(questions.size());
            randomQuestions.add(questions.stream().skip(randomIndex).findFirst().orElseThrow());
        }
        return randomQuestions;
    }
}
