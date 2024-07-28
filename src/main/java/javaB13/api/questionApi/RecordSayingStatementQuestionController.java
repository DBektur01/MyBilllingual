package javaB13.api.questionApi;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import javaB13.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionRequest;
import javaB13.dto.requests.questions.record_saying_statement.RecordSayingStatementQuestionUpdateRequest;
import javaB13.dto.responses.SimpleResponse;
import javaB13.services.questions.RecordSayingStatementQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/questions/record-saying-statement")
@RequiredArgsConstructor
@CrossOrigin
public class RecordSayingStatementQuestionController {
    private final RecordSayingStatementQuestionService questionService;

    @Operation(summary = "This is save Record saying statement question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveRecordSayingStatementQuestion(@RequestBody @Valid RecordSayingStatementQuestionRequest request) {
        return questionService.saveRecordSayingStatement(request);
    }

    @Operation(summary = "This is update by id Record saying statement question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateRespondNWordsQuestionById(@PathVariable Long id, @RequestBody @Valid
    RecordSayingStatementQuestionUpdateRequest updateRequest) {
        return questionService.updateRecordSayingStatementQuestion(id, updateRequest);
    }
}
