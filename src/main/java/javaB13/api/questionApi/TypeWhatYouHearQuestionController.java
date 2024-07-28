package javaB13.api.questionApi;

import  javaB13.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionRequest;
import  javaB13.dto.requests.questions.type_what_you_hear.TypeWhatYouHearQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.services.questions.TypeWhatYouHearQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/type-what-you-hear")
@RequiredArgsConstructor
@CrossOrigin
public class TypeWhatYouHearQuestionController {
    private final TypeWhatYouHearQuestionService questionService;

    @Operation(summary = "This is save Type what you hear question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveTypeWhatYouHearQuestion(@RequestBody @Valid TypeWhatYouHearQuestionRequest request) {
        return questionService.saveTypeWhatYouHearQuestion(request);
    }

    @Operation(summary = "This is update by id Type what you hear question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateTypeWhatYouHearQuestion(@PathVariable Long id, @RequestBody @Valid
    TypeWhatYouHearQuestionUpdateRequest updateRequest) {
        return questionService.updateTypeWhatYouHear(id, updateRequest);
    }
}
