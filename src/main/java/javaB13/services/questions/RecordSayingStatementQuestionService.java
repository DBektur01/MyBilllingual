package javaB13.services.questions;

import javaB13.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import javaB13.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import javaB13.dto.responses.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public interface RecordSayingStatementQuestionService {

    SimpleResponse saveRecordSayingStatement(RecordSayingStatementQuestionRequest request);

    SimpleResponse updateRecordSayingStatementQuestion(Long id, RecordSayingStatementQuestionUpdateRequest updateRequest);
}
