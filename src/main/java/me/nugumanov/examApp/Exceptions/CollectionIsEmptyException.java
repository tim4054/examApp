package me.nugumanov.examApp.Exceptions;

public class CollectionIsEmptyException extends RuntimeException{
    public CollectionIsEmptyException() {
        super("Коллекция пуста");
    }
}
