package com.example.exam_app.service;

import com.example.exam_app.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();


    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question remove(String question, String answer) {
        Question questionToRemove = new Question(question, answer);
        if (questions.remove(questionToRemove)) {
            return questionToRemove;
        }
        throw new RuntimeException("Question not found" + question);
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("No question available");
        }

        int randomIndex = random.nextInt(questions.size());
        Iterator<Question> iterator = questions.iterator();

        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }

        return iterator.next();
    }
}
