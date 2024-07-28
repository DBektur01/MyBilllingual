package javaB13.repositories.custom;

import  javaB13.dto.responses.result.EvaluatingSubmittedResultResponse;
import  javaB13.dto.responses.result.SubmittedResultsResponse;
import  javaB13.dto.responses.userResult.MyResultResponse;

import java.util.List;

public interface CustomResultRepository {
    List<MyResultResponse> getAll(Long userId);
    List<SubmittedResultsResponse> getAllSubmittedResults();
    EvaluatingSubmittedResultResponse getByIdEvaluatingSubmittedResult(Long resultId);
    Long getResultIdByAnswerId(Long answerId);
}
