package javaB13.repositories.custom;

import  javaB13.dto.responses.answer.UserAnswerResponse;

import java.util.List;

public interface CustomAnswerRepository {
    List<UserAnswerResponse> getAnswerResponsesByResultId(Long resultId);
    List<UserAnswerResponse> getAnswerResponsesByQuestionId(Long questionId, Long userId);
}
