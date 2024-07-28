package javaB13.services.questions;

import javaB13.dto.requests.questions.select_best_title.SelectBestTitleQuestionRequest;
import javaB13.dto.requests.questions.select_best_title.SelectBestTitleQuestionUpdateRequest;
import javaB13.dto.responses.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public interface SelectBestTitleQuestionService {

    SimpleResponse save(SelectBestTitleQuestionRequest request);
    SimpleResponse update(Long id, SelectBestTitleQuestionUpdateRequest request);
}
