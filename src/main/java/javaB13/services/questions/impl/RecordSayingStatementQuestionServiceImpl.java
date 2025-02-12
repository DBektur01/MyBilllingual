package javaB13.services.questions.impl;

import  javaB13.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import  javaB13.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.entity.Question;
import  javaB13.entity.Test;
import  javaB13.enums.QuestionType;
import  javaB13.exceptions.NotFoundException;
import  javaB13.repositories.QuestionRepository;
import  javaB13.repositories.TestRepository;
import  javaB13.services.questions.RecordSayingStatementQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordSayingStatementQuestionServiceImpl implements RecordSayingStatementQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    @Override
    public SimpleResponse saveRecordSayingStatement(RecordSayingStatementQuestionRequest request) {
        log.info("Saving record saying statement question");
        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new NotFoundException(String.format("Test with ID %s doesn't exist", request.getTestId())));

        Question question = Question.builder()
                .questionType(QuestionType.RECORD_SAYING_STATEMENT)
                .title(request.getTitle())
                .statement(request.getStatement())
                .correctAnswer(request.getCorrectAnswer())
                .questionOrder(request.getQuestionOrder())
                .duration(request.getDuration())
                .test(test)
                .isActive(request.getIsActive())
                .build();
        questionRepository.save(question);

        log.info("Question with title '{}' successfully saved", request.getTitle());
        return SimpleResponse.builder()
                .message(String.format("Question with title '%s' successfully saved", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateRecordSayingStatementQuestion(Long id, RecordSayingStatementQuestionUpdateRequest updateRequest) {
        log.info("Updating record saying statement question with ID: {}", id);
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Question with ID %s doesn't exist", id)));

        // TODO UPDATE LOGIC
        question.setTitle(updateRequest.getTitle() != null ? updateRequest.getTitle() : question.getTitle());
        question.setStatement(updateRequest.getStatement() != null ? updateRequest.getStatement() : question.getStatement());
        question.setDuration(updateRequest.getDuration() != null ? updateRequest.getDuration() : question.getDuration());
        question.setCorrectAnswer(updateRequest.getCorrectAnswer() != null ? updateRequest.getCorrectAnswer() : question.getCorrectAnswer());
        question.setQuestionOrder(updateRequest.getQuestionOrder() != null ? updateRequest.getQuestionOrder() : question.getQuestionOrder());
        question.setIsActive(updateRequest.getIsActive() != null ? updateRequest.getIsActive() : question.getIsActive());

        questionRepository.save(question);

        log.info("Question with ID {} successfully updated", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s successfully updated", id))
                .build();
    }
}