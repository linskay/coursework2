package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    @BeforeEach
    @DisplayName("Инициализация тестовых вопросов")
    void setUp() {
        javaQuestionService = new JavaQuestionService();

        Question question1 = new Question("Что такое Java?", "Java - это язык программирования");
        Question question2 = new Question("Что такое Spring?", "Spring - это фреймворк для Java");
        Question question3 = new Question("Что такое Maven?", "Maven - это инструмент сборки");

        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);
        javaQuestionService.addQuestion(question3);
    }

    @Test
    @DisplayName("Получение вопросов в пределах лимита")
    void getQuestionsWithinLimit() throws TooManyQuestionsException {
        Set<Question> questions = javaQuestionService.getQuestions(2);
        Assertions.assertEquals(2, questions.size());
    }

    @Test
    @DisplayName("Получение вопросов, превышающих лимит")
    void getQuestionsExceedingLimit() {
        Assertions.assertThrows(TooManyQuestionsException.class, () -> javaQuestionService.getQuestions(6));
    }

    @Test
    @DisplayName("Добавление и удаление вопроса")
    void addAndRemoveQuestion() {
        Question question = new Question("Что такое Java?", "Java - это язык программирования");
        javaQuestionService.addQuestion(question);

        Set<Question> questions = javaQuestionService.getQuestions(1);
        Assertions.assertEquals(1, questions.size());
        Assertions.assertTrue(questions.contains(question));

        javaQuestionService.removeQuestion(question);
        questions = javaQuestionService.getQuestions(1);
        Assertions.assertEquals(0, questions.size());
    }

    @Test
    @DisplayName("Получение случайного вопроса")
    void getRandomQuestion() {
        Question randomQuestion = javaQuestionService.getRandomQuestion();
        Assertions.assertTrue(javaQuestionService.getAllQuestions().contains(randomQuestion));
    }
}
