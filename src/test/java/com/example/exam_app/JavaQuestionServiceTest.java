package com.example.exam_app;

import com.example.exam_app.model.Question;
import com.example.exam_app.service.JavaQuestionService;
import com.example.exam_app.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestion() {
        Question question = javaQuestionService.add("What is Java?", "Programming language");

        assertNotNull(question);
        assertEquals("What is Java?", question.getQuestion());
        assertEquals("Programming language", question.getAnswer());

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertEquals(1, allQuestions.size());
        assertTrue(allQuestions.contains(question));
    }

    @Test
    void testRemoveQuestion() {
        javaQuestionService.add("What is Java?", "Programming language");
        Question removed = javaQuestionService.remove("What is Java?", "Programming language");

        assertNotNull(removed);
        assertEquals("What is Java?", removed.getQuestion());
        assertEquals("Programming language", removed.getAnswer());

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertTrue(allQuestions.isEmpty());
    }

    @Test
    void testRemoveNotNonExistentQuestion() {
        javaQuestionService.add("What is Java?", "Programming language");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            javaQuestionService.remove("What is Python?", "Programming language");
        });

        assertEquals("Question not found: What is Python?", exception.getMessage());
    }

    @Test
    void testGetAllQuestions() {
        javaQuestionService.add("Question1", "Answer1");
        javaQuestionService.add("Question2", "Answer2");

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertEquals(2, allQuestions.size());
    }

    @Test
    void testGetRandomQuestion() {
        javaQuestionService.add("Question1", "Answer1");
        javaQuestionService.add("Question2", "Answer2");
        javaQuestionService.add("Question3", "Answer3");

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
        assertTrue(randomQuestion.getQuestion().startsWith("Q"));
    }

    @Test
    void testGetRandomQuestionFromEmptyList() {
        assertThrows(IllegalStateException.class, () -> javaQuestionService.getRandomQuestion());
    }
}
