package me.nugumanov.examApp.services;

import me.nugumanov.examApp.ExaminerService;
import me.nugumanov.examApp.QuestionService;
import me.nugumanov.examApp.models.Question;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class ExaminerServiceImpl implements ExaminerService {
    QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.amountOfQuestions()) {
            throw new RuntimeException("бэд реквест");
        }
        Set<Question> questionSet = new HashSet<>();
        while (questionSet.size() != amount) {
            questionSet.add(questionService.getRandomQuestion());
        }
        return questionSet;
    }

    //Test
    public static void main(String[] args) {
        ExaminerService examinerService = new ExaminerServiceImpl(new JavaQuestionService());

        System.out.println(examinerService.getQuestions(1));
        System.out.println(examinerService.getQuestions(2));
        System.out.println(examinerService.getQuestions(3));
        System.out.println(examinerService.getQuestions(4));
        System.out.println(examinerService.getQuestions(5));


    }
}
