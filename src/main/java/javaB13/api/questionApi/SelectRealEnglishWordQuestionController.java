package javaB13.api.questionApi;

import  javaB13.dto.requests.questions.select_real_english.SelectRealEnglishWordRequest;
import  javaB13.dto.requests.questions.select_real_english.SelectRealEnglishWordUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.services.questions.SelectRealEnglishWordService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/select-real-english-word")
@RequiredArgsConstructor
@CrossOrigin
public class SelectRealEnglishWordQuestionController {
    private final SelectRealEnglishWordService questionService;

    @Operation(summary = "This is save Select-Real-English-Word question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveSelectRealEnglishWordQuestion(@RequestBody @Valid SelectRealEnglishWordRequest request) {
        return questionService.saveSelectRealEnglishWordQuestion(request);
    }
    @Operation(summary = "This is update by id Select-Real-English-Word question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateSelectRealEnglishWordQuestionById(@PathVariable Long id, @RequestBody @Valid
    SelectRealEnglishWordUpdateRequest updateRequest) {
        return questionService.updateSelectRealEnglishWordQuestion(id, updateRequest);
    }
}
