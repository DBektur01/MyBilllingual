package javaB13.services;

import javaB13.dto.requests.answer.AnswerRequest;
import javaB13.dto.responses.SimpleResponse;
import javaB13.entity.Answer;
import javaB13.entity.Question;
import javaB13.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerService {
    List<Answer> createAnswers(List<AnswerRequest> answerRequests, User user);
    Answer createAnswer(AnswerRequest answerRequest, User user, Question question);
    SimpleResponse evaluating(Long answerId, float score);
}
