package javaB13.services.questions;

import  javaB13.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsRequest;
import  javaB13.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public interface ListenAndSelectEnglishWordsService {
    SimpleResponse save(ListenAndSelectEnglishWordsRequest request);

    SimpleResponse update(ListenAndSelectEnglishWordsUpdateRequest updateListenAndSelectRequest, Long id);
}
