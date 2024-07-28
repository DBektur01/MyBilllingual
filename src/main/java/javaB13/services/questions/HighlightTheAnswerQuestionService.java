package javaB13.services.questions;

import  javaB13.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionRequest;
import  javaB13.dto.requests.questions.highlight_the_answer.HighlightTheAnswerQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;

public interface HighlightTheAnswerQuestionService {
    SimpleResponse saveHighlightTheAnswerQuestion(HighlightTheAnswerQuestionRequest request);

    SimpleResponse updateHighlightTheAnswerQuestionById(Long id, HighlightTheAnswerQuestionUpdateRequest updateRequest);

}
