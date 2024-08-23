package me.nugumanov.examApp.Exceptions;

public class NotFoundQuestion extends RuntimeException {
    public NotFoundQuestion() {
        super("Вопрос не найден");
    }
}
