package com.pro.sky.java.course2.controller;

import com.pro.sky.java.course2.service.Question;
import com.pro.sky.java.course2.service.ExaminerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/exam")
public class ExamController {
    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/questions")
    public ResponseEntity<Object> getQuestions(@RequestParam(value = "amount", defaultValue = "5") int amount) {
        List<Question> questions = examinerService.getQuestions(amount);
        return ResponseEntity.ok(questions);
    }
}
