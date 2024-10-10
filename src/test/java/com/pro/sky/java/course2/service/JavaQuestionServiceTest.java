package com.pro.sky.java.course2.service;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.exeption.IncorrectParameterException;
import com.pro.sky.java.course2.exeption.QuestionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        javaQuestionService.add(question1);
        javaQuestionService.add(question2);
        javaQuestionService.add(question3);
    }

    @Test
    @DisplayName("Корректный тест на добавление")
    void test1() {
        Question actual = javaQuestionService.add(new Question("Ыыыы1", "Сам ыыы1"));
        assertNotNull(actual);
        assertEquals(4, javaQuestionService.getAll().size());
    }

    @Test
    @DisplayName("Корректный тест на удаление")
    void test2() {
        assertNull(javaQuestionService.remove(question1));
        assertEquals(2, javaQuestionService.getAll().size());
    }

    @Test
    @DisplayName("Тест на выброс исключения на некорректный параметр")
    void test3() {
        assertThrows(IncorrectParameterException.class, () -> javaQuestionService.add(null));
    }

    @Test
    @DisplayName("Тест на выброс исключения на удаление вопроса которого нет")
    void test4() {
        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(new Question("gjgf", "vehfdmz")));
    }

    @Test
    @DisplayName("Положительный тест на возвращение коллекции")
    void test5() {
        Question q = new Question("ыыы111", "шото223344");
        javaQuestionService.add(q);
        assertEquals(4, javaQuestionService.getAll().size());
        Collection<Question> collectionExpected = new ArrayList<>(
                List.of(question1, question2, question3, q)
        );
        assertEquals(collectionExpected.size(), javaQuestionService.getAll().size());
    }
}
