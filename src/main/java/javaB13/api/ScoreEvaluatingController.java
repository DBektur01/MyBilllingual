package javaB13.api;


import io.swagger.v3.oas.annotations.Operation;
import javaB13.dto.responses.SimpleResponse;
import javaB13.services.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluating")
@RequiredArgsConstructor
@CrossOrigin
public class ScoreEvaluatingController {
    private final AnswerService answerService;

    @PostMapping("/{answerId}")
    @Operation(summary = "This is evaluating score method")
    public SimpleResponse evaluating(@PathVariable Long answerId, @RequestBody float score){
        return answerService.evaluating(answerId, score);
    }

}
