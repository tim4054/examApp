package me.nugumanov.examApp;

import me.nugumanov.examApp.models.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
