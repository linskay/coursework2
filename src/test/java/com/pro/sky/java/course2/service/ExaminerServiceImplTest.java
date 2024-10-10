package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.exeption.IncorrectParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    @DisplayName("Проверка на налл, выброс ошибки")
    void test1() {
        assertThrows(IncorrectParameterException.class, () -> examinerService.getQuestions(0));
        Mockito.verify(questionService, Mockito.never()).getRandom();
    }

    @Test
    @DisplayName("Проверка на минус, выброс ошибки")
    void test2() {
        assertThrows(IncorrectParameterException.class, () -> examinerService.getQuestions(-10));
        Mockito.verify(questionService, Mockito.never()).getRandom();
    }

    @Test
    @DisplayName("Проверка на большее количество вопросов, чем есть в коллекции, выброс ошибки")
    void test3() {
        assertThrows(IncorrectParameterException.class, () -> examinerService.getQuestions(10));
        Mockito.verify(questionService, Mockito.never()).getRandom();
    }
}