package me.nugumanov.examApp;

import me.nugumanov.examApp.models.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

    int amountOfQuestions();
}
