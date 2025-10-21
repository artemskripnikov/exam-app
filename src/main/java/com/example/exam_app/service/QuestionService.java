package com.example.exam_app.service;

import com.example.exam_app.model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question remove(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
