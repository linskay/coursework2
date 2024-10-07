package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    @DisplayName("Инициализация тестовых вопросов")
    void setUp() {
        question1 = new Question("Что такое Java?", "Java - это язык программирования");
        question2 = new Question("Что такое Spring?", "Spring - это фреймворк для Java");
        question3 = new Question("Что такое Maven?", "Maven - это инструмент сборки");
    }

    @Test
    @DisplayName("Получение вопросов в пределах лимита")
    void getQuestionsWithinLimit() throws TooManyQuestionsException {
        Set<Question> expectedQuestions = new HashSet<>();
        expectedQuestions.add(question1);
        expectedQuestions.add(question2);
        when(javaQuestionService.getQuestions(2)).thenReturn(expectedQuestions);

        Set<Question> questions = javaQuestionService.getQuestions(2);
        assertEquals(2, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
    }

    @Test
    @DisplayName("Получение вопросов, превышающих лимит")
    void getQuestionsExceedingLimit() {
        Assertions.assertThrows(TooManyQuestionsException.class, () -> javaQuestionService.getQuestions(6));
    }

    @Test
    @DisplayName("Добавление и удаление вопроса")
    void addAndRemoveQuestion() {

        Set<Question> expectedQuestions = new HashSet<>();
        expectedQuestions.add(question1);
        when(javaQuestionService.getQuestions(1)).thenReturn(expectedQuestions);

        javaQuestionService.addQuestion(question1);

        Set<Question> questions = javaQuestionService.getQuestions(1);
        assertEquals(1, questions.size());
        assertTrue(questions.contains(question1));

        javaQuestionService.removeQuestion(question1);

        questions = javaQuestionService.getQuestions(1);
        assertEquals(1, questions.size());
    }

    @Test
    @DisplayName("Получение случайного вопроса")
    void getRandomQuestion() {

        when(javaQuestionService.getRandomQuestion()).thenReturn(question1);

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        Mockito.verify(javaQuestionService, times(1)).getRandomQuestion();

        assertEquals(question1, randomQuestion);
    }
}
