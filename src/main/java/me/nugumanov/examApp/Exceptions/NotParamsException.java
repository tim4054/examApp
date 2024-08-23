package me.nugumanov.examApp.Exceptions;

public class NotParamsException extends RuntimeException {
    public NotParamsException() {
        super("Отсутствует параметр");
    }

}
