package javaB13.api.questionApi;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import javaB13.dto.requests.questions.select_best_title.SelectBestTitleQuestionRequest;
import javaB13.dto.requests.questions.select_best_title.SelectBestTitleQuestionUpdateRequest;
import javaB13.dto.responses.SimpleResponse;
import javaB13.services.questions.SelectBestTitleQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/select-best-title")
@RequiredArgsConstructor
@CrossOrigin
public class SelectBestTitleQuestionController {
    private final SelectBestTitleQuestionService questionService;

    @Operation(summary = "This is save Select-Best-Title question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse save(@RequestBody @Valid SelectBestTitleQuestionRequest request) {
        return questionService.save(request);
    }

    @Operation(summary = "This is update by id Select-Best-Title question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody @Valid SelectBestTitleQuestionUpdateRequest request) {
        return questionService.update(id, request);
    }
}
