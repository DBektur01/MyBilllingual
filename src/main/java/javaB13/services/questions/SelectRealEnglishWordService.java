package javaB13.services.questions;


import javaB13.dto.requests.questions.select_real_english.SelectRealEnglishWordRequest;
import javaB13.dto.requests.questions.select_real_english.SelectRealEnglishWordUpdateRequest;
import javaB13.dto.responses.SimpleResponse;

public interface SelectRealEnglishWordService {
    SimpleResponse saveSelectRealEnglishWordQuestion(SelectRealEnglishWordRequest request);
    SimpleResponse updateSelectRealEnglishWordQuestion(Long id, SelectRealEnglishWordUpdateRequest request);
}
