package javaB13.services.impl;

import  javaB13.dto.requests.answer.AnswerRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.entity.*;
import  javaB13.enums.AnswerStatus;
import  javaB13.enums.QuestionType;
import  javaB13.enums.ResultStatus;
import  javaB13.exceptions.NotFoundException;
import  javaB13.repositories.AnswerRepository;
import  javaB13.repositories.OptionRepository;
import  javaB13.repositories.QuestionRepository;
import  javaB13.repositories.ResultRepository;
import  javaB13.repositories.custom.CustomResultRepository;
import  javaB13.services.AnswerService;
import  javaB13.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final FileService fileService;
    private final AnswerRepository answerRepository;
    private final CustomResultRepository customResultRepository;
    private final ResultRepository resultRepository;

    @Override
    public List<Answer> createAnswers(List<AnswerRequest> answerRequests, User user) {
        List<Answer> answers = new ArrayList<>();

        for (AnswerRequest answerRequest : answerRequests) {
            Question question = questionRepository.findById(answerRequest.getQuestionId()).orElseThrow(() ->
                    new NotFoundException(String.format("Question with id : %s doesn't exist !", answerRequest.getQuestionId())));
            Answer answer = createAnswer(answerRequest, user, question);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public Answer createAnswer(AnswerRequest answerRequest, User user, Question question) {
        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setAnswerStatus(AnswerStatus.NOT_EVALUATED);

        if (answerRequest.getOptionsIds() != null) {
            List<Option> options = optionRepository.findAllById(answerRequest.getOptionsIds());
            answer.setOptions(options);
        }

        if (answerRequest.getNumberOfPlays() != null) {
            answer.setNumberOfPlays(answerRequest.getNumberOfPlays());
        }

        if (answerRequest.getFileUrl() != null && question.getQuestionType().equals(QuestionType.RECORD_SAYING_STATEMENT)) {
            File file = fileService.createAudioFile(answerRequest.getFileUrl(), question);
            answer.setFiles(Collections.singletonList(file));
        }

        if (answerRequest.getData() != null) {
            if (isDataApplicableForAnswer(answerRequest.getData(), question)) {
                answer.setData(answerRequest.getData());
                answer.setNumberOfWords(answerRequest.getData().split(" ").length);
            }
        }
        return answer;
    }

    @Override
    public SimpleResponse evaluating(Long answerId, float score) {

        Answer answer = answerRepository.findById(answerId).orElseThrow(() ->
                new NotFoundException(String.format("Answer with id : %s doesn't exist !", answerId)));

        answer.setEvaluatedScore(score);
        answer.setAnswerStatus(AnswerStatus.EVALUATED);

        Long resultIdByAnswerId = customResultRepository.getResultIdByAnswerId(answerId);

        Result result = resultRepository.findById(resultIdByAnswerId).orElseThrow(()->
                new NotFoundException(String.format("Answer with id : %s doesn't exist !", answerId)));

        boolean allTrue = result.getAnswers().stream()
                .allMatch(a -> a.getAnswerStatus() == AnswerStatus.EVALUATED);

        if (allTrue){
            result.setStatus(ResultStatus.EVALUATED);
        }else result.setStatus(ResultStatus.NOT_EVALUATED);
        result.setScore(result.getScore() + score);

        resultRepository.save(result);
        answerRepository.save(answer);

        return SimpleResponse.builder().message(String.format("Answer with : %d id successfully scored", answerId)).build();
    }

    private boolean isDataApplicableForAnswer(String data, Question question) {
        return question.getQuestionType() == QuestionType.DESCRIBE_IMAGE
                || question.getQuestionType() == QuestionType.HIGHLIGHT_THE_ANSWER
                || question.getQuestionType() == QuestionType.RESPOND_N_WORDS
                || question.getQuestionType() == QuestionType.TYPE_WHAT_YOU_HEAR;
    }
}
