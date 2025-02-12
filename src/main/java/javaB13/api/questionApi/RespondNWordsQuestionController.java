package javaB13.api.questionApi;

import  javaB13.dto.requests.questions.respond_n_words.RespondNWordsQuestionRequest;
import  javaB13.dto.requests.questions.respond_n_words.RespondNWordsQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.services.questions.RespondNWordsQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/respond-n-words")
@RequiredArgsConstructor
@CrossOrigin
public class RespondNWordsQuestionController {
    private final RespondNWordsQuestionService questionService;

    @Operation(summary = "This is save Respond N words question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveRespondNWordsQuestion(@RequestBody @Valid RespondNWordsQuestionRequest request) {
        return questionService.saveRespondNWordsQuestion(request);
    }

    @Operation(summary = "This is update by id Respond N words question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateRespondNWordsQuestionById(@PathVariable Long id, @RequestBody @Valid
    RespondNWordsQuestionUpdateRequest updateRequest) {
        return questionService.updateRespondNWordsQuestionById(id, updateRequest);
    }
}
