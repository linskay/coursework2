package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    private ExaminerService examinerService;

    private Question question1;
    private Question question2;
    private Question question3;
    private Question question4;
    private Question question5;

    @BeforeEach
    @DisplayName("Инициализация тестовых данных")
    void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService);

        question1 = new Question("Что такое Java?", "Java - это язык программирования");
        question2 = new Question("Что такое Spring?", "Spring - это фреймворк для Java");
        question3 = new Question("Что такое Maven?", "Maven - это инструмент сборки");
        question4 = new Question("Что такое HashSet?", "HashSet - это реализация множества (набора), которая хранит уникальные элементы");
        question5 = new Question("Что такое Переменная?", "Переменная - это ячейка памяти, в которой хранится значение");
    }

    @Test
    @DisplayName("Получение вопросов - корректный случай")
    void getQuestions_CorrectCase() throws TooManyQuestionsException {

        Set<Question> expectedQuestions = new HashSet<>();
        expectedQuestions.add(question1);
        expectedQuestions.add(question2);
        expectedQuestions.add(question3);
        expectedQuestions.add(question4);
        expectedQuestions.add(question5);
        when(javaQuestionService.getQuestions(5)).thenReturn(expectedQuestions);

        List<Question> questions = examinerService.getQuestions(5, "Вопрос", "Ответ");
        assertEquals(5, questions.size());
    }

    @Test
    @DisplayName("Получение вопросов для экзамена при превышении лимита")
    void getQuestionsToAskExceedingLimit() {
        Assertions.assertThrows(TooManyQuestionsException.class, () -> examinerService.getQuestions(10, "", ""));
    }

    @Test
    @DisplayName("Получение вопросов для экзамена при недостаточном количестве вопросов")
    void getQuestionsToAskInsufficientQuestions() {
        when(javaQuestionService.getQuestions(5)).thenReturn(new HashSet<>());
        Assertions.assertThrows(TooManyQuestionsException.class, () -> examinerService.getQuestions(5, "", ""));
    }
}
