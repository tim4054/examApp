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

//    private final Set<Question> questions = new HashSet<>(List.of(
//            new Question("Вопрос1", "Ответ1"),
//            new Question("Вопрос2", "Ответ2"),
//            new Question("Вопрос3", "Ответ3"),


//            new Question("Вопрос4", "Ответ4"),
//            new Question("Вопрос5", "Ответ5")));


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
    public Question remove(Question question) {

        checkCollectionIsEmpty();

        if (questions.contains(question)) {
            questions.remove(question);
        } else {
            throw new NotFoundQuestion();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {

        checkCollectionIsEmpty();

        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {

        checkCollectionIsEmpty();

        List<Question> questionsList = new ArrayList<>(questions);
        Random random = new Random();
        return questionsList.get(random.nextInt(0, amountOfQuestions()));
    }

    @Override
    public int amountOfQuestions() {
        return questions.size();
    }

    public void checkCollectionIsEmpty() {
        if (questions.isEmpty()) {
            throw new CollectionIsEmptyException();
        }
    }

    public void checkNotParams(String question, String answer) {
        if (question == null || answer == null) {
            throw new NotParamsException();
        }
    }


    //Tests
    public static void main(String[] args) {
        JavaQuestionService service = new JavaQuestionService();
        Question question1 = new Question("Вопрос1", "Ответ1");
        Question question2 = new Question("Вопрос2", "Ответ2");
        Question question3 = new Question("Вопрос3", "Ответ3");
        Question question4 = new Question("Вопрос4", "Ответ4");
        Question question5 = new Question("Вопрос5", "Ответ5");
        Question questionToRemove = new Question("Удалить", "Вопрос");

        service.add(question1);
        service.add(question2);
        service.add(question3);
        service.add(question4);
        service.add(question5);
        service.add(questionToRemove);

        service.add("ВопросДва", "ОтветДва");

        service.remove(questionToRemove);

        System.out.println(service.getRandomQuestion());

        System.out.println(service.getAll());
    }
}
