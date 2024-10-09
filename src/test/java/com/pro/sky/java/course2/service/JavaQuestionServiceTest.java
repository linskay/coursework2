package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;

    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
        question1 = new Question("Что такое Java?", "Java - это язык программирования");
        question2 = new Question("Что такое Spring?", "Spring - это фреймворк для Java");
        question3 = new Question("Что такое Maven?", "Maven - это инструмент сборки");
        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);
        javaQuestionService.addQuestion(question3);
    }

    @Test
    @DisplayName("Получение вопросов в пределах лимита")
    void getQuestionsWithinLimit() throws TooManyQuestionsException {
        Set<Question> questions = javaQuestionService.getQuestions(2);
        assertEquals(2, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
    }

    @Test
    @DisplayName("Получение вопросов, превышающих лимит")
    void getQuestionsExceedingLimit() {
        javaQuestionService.addQuestion(new Question("Вопрос 4", "Ответ 4"));
        javaQuestionService.addQuestion(new Question("Вопрос 5", "Ответ 5"));
        javaQuestionService.addQuestion(new Question("Вопрос 6", "Ответ 6"));
        javaQuestionService.addQuestion(new Question("Вопрос 7", "Ответ 7"));
        assertThrows(TooManyQuestionsException.class, () -> javaQuestionService.getQuestions(6));
    }

    @Test
    @DisplayName("Добавление и удаление вопроса")
    void addAndRemoveQuestion() {
        assertEquals(3, javaQuestionService.getQuestions(3).size());
        javaQuestionService.addQuestion(question1);
        assertEquals(3, javaQuestionService.getQuestions(3).size());
        javaQuestionService.removeQuestion(question1);
        assertEquals(2, javaQuestionService.getQuestions(3).size());
    }

    @Test
    @DisplayName("Получение случайного вопроса")
    void getRandomQuestion() {
        Collection<Question> randomQuestions = javaQuestionService.getRandomQuestion(1);
        assertFalse(randomQuestions.isEmpty());
    }
}
