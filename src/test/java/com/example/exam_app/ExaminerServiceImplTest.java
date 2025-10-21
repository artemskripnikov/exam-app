package com.example.exam_app;

import com.example.exam_app.model.Question;
import com.example.exam_app.service.ExaminerServiceImpl;
import com.example.exam_app.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private Set<Question> testQuestions;

    @BeforeEach
    void setUp() {
        testQuestions = new HashSet<>(Arrays.asList(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3"),
                new Question("Q4", "A4"),
                new Question("Q5", "A5")
        ));
    }

    @Test
    void testGetQuestionsValidAmount() {
        when(javaQuestionService.getAll()).thenReturn(testQuestions);


        when(javaQuestionService.getRandomQuestion())
                .thenReturn(new Question("Q1", "A1"))
                .thenReturn(new Question("Q2", "A2"))
                .thenReturn(new Question("Q3", "A3"));

        Collection<Question> result = examinerService.getQuestions(3);

        assertEquals(3, result.size());
        verify(javaQuestionService, atLeast(3)).getRandomQuestion();
    }

    @Test
    void testGetQuestionsAllUnique() {
        when(javaQuestionService.getAll()).thenReturn(testQuestions);


        when(javaQuestionService.getRandomQuestion())
                .thenReturn(new Question("Q1", "A1"))
                .thenReturn(new Question("Q2", "A2"))
                .thenReturn(new Question("Q3", "A3"));

        Collection<Question> result = examinerService.getQuestions(3);

        assertEquals(3, new HashSet<>(result).size());
    }

    @Test
    void testGetQuestionsAmountTooLarge() {
        when(javaQuestionService.getAll()).thenReturn(testQuestions);

        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(10));
    }

    @Test
    void testGetQuestionsZeroAmount() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(0));
    }

    @Test
    void testGetQuestionsNegativeAmount() {
        assertThrows(ResponseStatusException.class, () -> examinerService.getQuestions(-1));
    }
}
