package javaB13.services.questions;

import  javaB13.dto.responses.SimpleResponse;
import  javaB13.dto.responses.questions.EvaluateQuestionResponse;
import  javaB13.dto.responses.questions.QuestionResponse;

import java.util.List;

public interface MainQuestionService {
    List<QuestionResponse> getAllQuestions();

    QuestionResponse getQuestionById(Long id);

    SimpleResponse deleteQuestionById(Long id);
    EvaluateQuestionResponse getEvaluateQuestionByIdes(Long answerId, Long questionId);
}
