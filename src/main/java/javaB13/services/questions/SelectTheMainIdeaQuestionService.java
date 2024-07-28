package javaB13.services.questions;
import  javaB13.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionRequest;
import  javaB13.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;

public interface SelectTheMainIdeaQuestionService {
    SimpleResponse saveSelectTheMainIdeaQuestion(SelectTheMainIdeaQuestionRequest request);
    SimpleResponse updateSelectTheMainQuestionById(Long id,SelectTheMainIdeaQuestionUpdateRequest request);

}
