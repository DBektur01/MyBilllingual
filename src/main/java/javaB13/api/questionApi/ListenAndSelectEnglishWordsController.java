package javaB13.api.questionApi;

import  javaB13.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsRequest;
import  javaB13.dto.requests.questions.listen_and_select_english_words.ListenAndSelectEnglishWordsUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.services.questions.ListenAndSelectEnglishWordsService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions/listen_and_select_english_words")
@RequiredArgsConstructor
@CrossOrigin
public class ListenAndSelectEnglishWordsController {
    private final ListenAndSelectEnglishWordsService listenAndSelectEnglishWordsService;

    @Operation(summary = "This is save Listen and select real english words question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveListenAndSelectEnglishWords(@RequestBody @Valid ListenAndSelectEnglishWordsRequest request) {
        return listenAndSelectEnglishWordsService.save(request);
    }

    @Operation(summary = "This is update Listen and select real english words question method")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse updateListenAndSelectEnglishWords(@RequestBody @Valid ListenAndSelectEnglishWordsUpdateRequest updateRequest,
                                                            @PathVariable Long id) {
        return listenAndSelectEnglishWordsService.update(updateRequest, id);
    }
}
