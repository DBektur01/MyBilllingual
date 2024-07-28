package javaB13.api.questionApi;

import  javaB13.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionRequest;
import  javaB13.dto.requests.questions.select_the_main_idea.SelectTheMainIdeaQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.services.questions.SelectTheMainIdeaQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/select-the-main-idea")
@RequiredArgsConstructor
@CrossOrigin
public class SelectTheMainIdeaQuestionController {
    private final SelectTheMainIdeaQuestionService questionService;

    @Operation(summary = "This is save  Select the main idea question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveSelectTheMainIdea(@RequestBody @Valid SelectTheMainIdeaQuestionRequest request) {
        return questionService.saveSelectTheMainIdeaQuestion(request);
    }

    @Operation(summary = "This is update by id Select the main idea question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateSelectTheMainIdea(@PathVariable Long id, @RequestBody @Valid
    SelectTheMainIdeaQuestionUpdateRequest updateRequest) {
        return questionService.updateSelectTheMainQuestionById(id, updateRequest);
    }
}
