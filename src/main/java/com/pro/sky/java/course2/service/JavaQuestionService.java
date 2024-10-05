package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
                result.addAll(questions.stream().skip(index).limit(1).toList());
            }
            return result;
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        return List.of();
    }

    @Override
    public Question getQuestionById(int id) {
        return null;
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void deleteQuestion(int id) {

    }

    @Override
    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        int randomIndex = new Random().nextInt(questions.size());
        return new HashSet<>(questions).stream()
                .skip(randomIndex)
                .findFirst()
                .orElse(null);
    }
}
