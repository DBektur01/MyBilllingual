package javaB13.services.questions;


import  javaB13.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import  javaB13.dto.requests.questions.respond_n_words.RespondNWordsQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;

public interface RespondNWordsQuestionService {
    SimpleResponse saveRespondNWordsQuestion(RespondNWordsQuestionRequest request);

    SimpleResponse updateRespondNWordsQuestionById(Long id, RespondNWordsQuestionUpdateRequest updateRequest);
}