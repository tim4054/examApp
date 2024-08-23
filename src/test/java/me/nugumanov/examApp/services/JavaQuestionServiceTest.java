package me.nugumanov.examApp.services;

import me.nugumanov.examApp.Exceptions.CollectionIsEmptyException;
import me.nugumanov.examApp.Exceptions.NotFoundQuestion;
import me.nugumanov.examApp.Exceptions.NotParamsException;
import me.nugumanov.examApp.models.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class JavaQuestionServiceTest {
    private final JavaQuestionService service = new JavaQuestionService();

    @Test
    void add_WhenCorrectParams_ThenAddQuestion() {
        Question expected = new Question("Вопрос1", "Ответ1");

        //test
        Question actual = service.add("Вопрос1", "Ответ1");

        //check
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("paramsStream")
    void add_WhenNullParams_ThenTrowsNotParamsException(String question, String answer) {
        //test & check
        Assertions.assertThrows(NotParamsException.class, () -> service.add(question, answer));
    }

    @Test
    void testAdd() {
    }

    @Test
    void remove_WhenCollectionContainsQuestion_ThenRemoveQuestion() {
        Question question = new Question("Вопрос", "Ответ");
        service.add(question);
        service.remove(question);

        //check
        assertThat(service.getQuestions()).isEmpty();
    }

    @Test
    void remove_WhenCollectionIsEmpty_ThenTrowsCollectionIsEmptyException() {
        //test & check
        Assertions.assertThrows(CollectionIsEmptyException.class,
                () -> service.remove(new Question("Вопрос", "Ответ")));
    }

    @Test
    void remove_WhenCollectionDoesNotContainQuestion_ThenTrowsNotFoundQuestion() {
        service.add(new Question("Вопрос", "Ответ"));

        //test & check
        Assertions.assertThrows(NotFoundQuestion.class,
                () -> service.remove(new Question("Вопрос1", "Ответ1")));
    }

    @Test
    void getAll_WhenCollectionIsNotEmpty_ThenReturnCollection() {
        service.add(new Question("Вопрос", "Ответ"));
        Collection<Question> expected = service.getQuestions();

        //test
        Collection<Question> actual = service.getAll();

        //check
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void getAll_WhenCollectionIsEmpty_ThenTrowsCollectionIsEmptyException() {
        //test & check
        Assertions.assertThrows(CollectionIsEmptyException.class,
                service::getAll);
    }

    @Test
    void getRandomQuestion() {
    }

    @Test
    void testGetRandomQuestion() {
    }

    public static Stream<Arguments> paramsStream() {
        return Stream.of(
                Arguments.of("Вопрос1", null),
                Arguments.of(null, "Ответ1"),
                Arguments.of(null, null)
        );
    }
}