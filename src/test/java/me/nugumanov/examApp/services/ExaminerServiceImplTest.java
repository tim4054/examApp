package me.nugumanov.examApp.services;

import me.nugumanov.examApp.Exceptions.QuestionAmountOverException;
import me.nugumanov.examApp.models.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


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
        verify(javaQuestionService, times(1)).amountOfQuestions();
        verify(javaQuestionService, times(1)).getRandomQuestion();
    }

    @Test
    @DisplayName("Выбрасывает исключение, " +
            "когда количество запрашиваемых вопросов превышает количество вопросов в коллекции")
    void getQuestions2() {
        when(javaQuestionService.amountOfQuestions()).thenThrow(QuestionAmountOverException.class);

        //test && check
        assertThatExceptionOfType(QuestionAmountOverException.class).
                isThrownBy(() -> examinerService.getQuestions(4));
        verify(javaQuestionService, times(1)).amountOfQuestions();
    }

    @Test
    @DisplayName("Выбрасывает исключение при минусовом количестве")
    void getQuestions3() {
        when(javaQuestionService.amountOfQuestions()).thenThrow(QuestionAmountOverException.class);

        //test && check
        assertThatExceptionOfType(QuestionAmountOverException.class).
                isThrownBy(() -> examinerService.getQuestions(-1));
        verify(javaQuestionService, times(1)).amountOfQuestions();
    }
}
