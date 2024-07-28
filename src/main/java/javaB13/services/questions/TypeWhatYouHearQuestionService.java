package javaB13.services.questions;

import  javaB13.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import  javaB13.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;

public interface TypeWhatYouHearQuestionService {

    SimpleResponse saveTypeWhatYouHearQuestion(TypeWhatYouHearQuestionRequest request);

    SimpleResponse updateTypeWhatYouHear(Long id, TypeWhatYouHearQuestionUpdateRequest updateQuestionRequest);
}
