package javaB13.services;

import javaB13.dto.responses.SimpleResponse;
import javaB13.dto.responses.result.EvaluatingSubmittedResultResponse;
import javaB13.dto.responses.result.SubmittedResultsResponse;
import javaB13.dto.responses.userResult.MyResultResponse;
import javaB13.entity.Answer;
import javaB13.entity.Result;
import javaB13.entity.Test;
import javaB13.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {
    List<MyResultResponse> getAll(Authentication authentication);

    SimpleResponse deleteById(Long id);
    Result createResult(Test test, User user, List<Answer> answers);
    List<SubmittedResultsResponse> getAll();
    EvaluatingSubmittedResultResponse getByIdEvaluatedSubmittedResult(Long resultId);
}
