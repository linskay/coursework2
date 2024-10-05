package com.pro.sky.java.course2.service;


import com.pro.sky.java.course2.exeption.TooManyQuestionsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionService;

    private ExaminerService examinerService;

    @BeforeEach
    @DisplayName("Инициализация ExaminerService")
    void setUp() {
        examinerService = new ExaminerServiceImpl(javaQuestionService);
    }

    @Test
    @DisplayName("Получение вопросов для экзамена")
    void getQuestionsToAsk() throws TooManyQuestionsException {
        Question question1 = new Question("Что такое Java?", "Java - это язык программирования");
        Question question2 = new Question("Что такое Spring?", "Spring - это фреймворк для Java");
        Question question3 = new Question("Что такое Maven?", "Maven - это инструмент сборки");

        Set<Question> mockQuestions = new HashSet<>();
        mockQuestions.add(question1);
        mockQuestions.add(question2);
        mockQuestions.add(question3);

        when(javaQuestionService.getQuestions(5)).thenReturn(mockQuestions);

        Set<Question> questionsToAsk = examinerService.getQuestionsToAsk(5);
        Assertions.assertEquals(5, questionsToAsk.size());
        Assertions.assertTrue(questionsToAsk.contains(question1));
        Assertions.assertTrue(questionsToAsk.contains(question2));
        Assertions.assertTrue(questionsToAsk.contains(question3));
    }

    @Test
    @DisplayName("Получение вопросов для экзамена при превышении лимита")
    void getQuestionsToAskExceedingLimit() {
        when(javaQuestionService.getQuestions(10)).thenReturn(new HashSet<>());

        Assertions.assertThrows(TooManyQuestionsException.class, () -> examinerService.getQuestionsToAsk(10));
    }

    @Test
    @DisplayName("Получение вопросов для экзамена при недостаточном количестве вопросов")
    void getQuestionsToAskInsufficientQuestions() throws TooManyQuestionsException {
        Set<Question> mockQuestions = new HashSet<>();

        mockQuestions.add(new Question("Что такое Java?", "Java - это язык программирования"));

        when(javaQuestionService.getQuestions(5)).thenReturn(mockQuestions);

        Assertions.assertThrows(TooManyQuestionsException.class, () -> examinerService.getQuestionsToAsk(5));
    }
}
