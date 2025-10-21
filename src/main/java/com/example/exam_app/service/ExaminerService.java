package com.example.exam_app.service;

import com.example.exam_app.model.Question;
import java.util.Collection;


public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
