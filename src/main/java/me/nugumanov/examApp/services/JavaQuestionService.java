package me.nugumanov.examApp.services;

import me.nugumanov.examApp.Exceptions.CollectionIsEmptyException;
import me.nugumanov.examApp.Exceptions.NotFoundQuestion;
import me.nugumanov.examApp.Exceptions.NotParamsException;
import me.nugumanov.examApp.QuestionService;
import me.nugumanov.examApp.models.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;

    private static final Random random = new Random();


    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    @Override
    public Question add(String question, String answer) {

        checkNotParams(question, answer);

        Question question1 = new Question(question, answer);
        questions.add(question1);
        return question1;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {

        checkCollectionIsEmpty("Нечего удалять, так как коллекция пуста");

        checkNotParams(question, answer);

        Question questionToRemove = new Question(question, answer);

        if (!questions.remove(questionToRemove)) {
            throw new NotFoundQuestion();
        }
        return questionToRemove;
    }

    @Override
    public Collection<Question> getAll() {

        checkCollectionIsEmpty("Нечего отображать, так как коллекция пуста");

        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {

        checkCollectionIsEmpty("Нечего отображать, так как коллекция пуста");

        List<Question> questionsList = new ArrayList<>(questions);
        return questionsList.get(random.nextInt(0, amountOfQuestions()));
    }

    @Override
    public int amountOfQuestions() {
        return questions.size();
    }

    public void checkCollectionIsEmpty(String message) {
        if (questions.isEmpty()) {
            throw new CollectionIsEmptyException(message);
        }
    }

    public void checkNotParams(String question, String answer) {
        if (question == null || answer == null) {
            throw new NotParamsException();
        }
    }
}
