package me.nugumanov.examApp.services;

import me.nugumanov.examApp.ExaminerService;
import me.nugumanov.examApp.Exceptions.QuestionAmountOverException;
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
        if (amount > questionService.amountOfQuestions() || amount < 0) {
            throw new QuestionAmountOverException();
        }
        Set<Question> questionSet = new HashSet<>();
        while (questionSet.size() < amount) {
            questionSet.add(questionService.getRandomQuestion());
        }
        return questionSet;
    }
}
