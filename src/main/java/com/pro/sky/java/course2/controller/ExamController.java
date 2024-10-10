package com.pro.sky.java.course2.controller;

import com.pro.sky.java.course2.Question;
import com.pro.sky.java.course2.service.ExaminerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService q) {
        this.examinerService = q;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getAmountQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
