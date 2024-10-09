package com.pro.sky.java.course2.controller;

import com.pro.sky.java.course2.service.JavaQuestionService;
import com.pro.sky.java.course2.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question,
                                                @RequestBody Question answer) {
        javaQuestionService.addQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteQuestion(@RequestBody Question question) {
        javaQuestionService.removeQuestion(question);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/random")
    public ResponseEntity<Collection<Question>> getRandomQuestion() {
        Collection<Question> randomQuestions = javaQuestionService.getRandomQuestion(1);
        if (randomQuestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(randomQuestions);
    }
}
