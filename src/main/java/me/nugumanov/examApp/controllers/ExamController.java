package me.nugumanov.examApp.controllers;

import me.nugumanov.examApp.ExaminerService;
import me.nugumanov.examApp.models.Question;
import me.nugumanov.examApp.services.ExaminerServiceImpl;
import me.nugumanov.examApp.services.JavaQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get/{amount}")
public class ExamController {
    ExaminerService service = new ExaminerServiceImpl(new JavaQuestionService());

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping()
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return service.getQuestions(amount);
    }

}
