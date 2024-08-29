package me.nugumanov.examApp.services;

import me.nugumanov.examApp.models.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Mock
    private JavaQuestionService javaQuestionService;


    @Test
    @DisplayName("Возвращает рандомный вопрос")
    void getQuestions1() {
        List<Question> questions = List.of(
                new Question("Вопрос1", "Ответ1"),
                new Question("Вопрос2", "Ответ2"),
                new Question("Вопрос3", "Ответ3")
        );
        Question expected = questions.get(new Random().nextInt(0, questions.size()));

        when(javaQuestionService.amountOfQuestions()).thenReturn(3);
        when(javaQuestionService.getRandomQuestion()).thenReturn(expected);

        //test
        Collection<Question> actual = examinerService.getQuestions(1);

        //check
        assertThat(actual).contains(expected);

    }

    @Test
    @DisplayName("Исключение")
    void getQuestions2() {
        List<Question> questions = List.of(
                new Question("Вопрос1", "Ответ1"),
                new Question("Вопрос2", "Ответ2"),
                new Question("Вопрос3", "Ответ3")
        );
        Question expected = questions.get(new Random().nextInt(0, questions.size()));

        when(javaQuestionService.amountOfQuestions()).thenReturn(3);
        when(javaQuestionService.getRandomQuestion()).thenReturn(expected);

        //test
        Collection<Question> actual = examinerService.getQuestions(1);

        //check
        assertThat(actual).contains(expected);

    }
}
