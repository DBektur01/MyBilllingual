package javaB13.services.impl;

import  javaB13.dto.responses.SimpleResponse;
import  javaB13.dto.responses.result.EvaluatingSubmittedResultResponse;
import  javaB13.dto.responses.result.SubmittedResultsResponse;
import  javaB13.dto.responses.userResult.MyResultResponse;
import  javaB13.entity.*;
import  javaB13.enums.ResultStatus;
import  javaB13.exceptions.NotFoundException;
import  javaB13.repositories.ResultRepository;
import  javaB13.repositories.custom.CustomResultRepository;
import  javaB13.services.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResultServiceImpl implements ResultService {
    private final CustomResultRepository customResultRepository;
    private final ResultRepository resultRepository;

    @Override
    public List<MyResultResponse> getAll(Authentication authentication) {
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        log.info("Fetching all results for user with ID: {}", userInfo.getId());
        List<MyResultResponse> results = customResultRepository.getAll(userInfo.getId());
        log.info("Retrieved {} results for user with ID: {}", results.size(), userInfo.getId());
        return results;
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        log.info("Deleting result with ID: {}", id);
        resultRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Result with ID %d not found", id)));
        resultRepository.deleteById(id);
        log.info("Result with ID {} deleted successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Result with ID %d successfully deleted", id))
                .build();
    }

    @Override
    public Result createResult(Test test, User user, List<Answer> answers) {
        Result result = new Result();
        result.setTest(test);
        result.setUser(user);
        result.setDateOfSubmission(LocalDateTime.now());
        result.setStatus(ResultStatus.NOT_EVALUATED);
        result.setScore(0); // todo answers score ...
        result.setAnswers(answers);
        return result;
    }

    @Override
    public List<SubmittedResultsResponse> getAll() {
        return customResultRepository.getAllSubmittedResults();
    }

    @Override
    public EvaluatingSubmittedResultResponse getByIdEvaluatedSubmittedResult(Long resultId) {
        return customResultRepository.getByIdEvaluatingSubmittedResult(resultId);
    }
}
