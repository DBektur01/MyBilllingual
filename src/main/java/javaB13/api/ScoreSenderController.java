package javaB13.api;

import jakarta.validation.Valid;
import javaB13.dto.responses.SimpleResponse;
import javaB13.services.ScoreSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sendEmail")
@RequiredArgsConstructor
@CrossOrigin
public class ScoreSenderController {

    private final ScoreSender scoreSender;

    @PostMapping("/{resultId}")
    public SimpleResponse sendEmail(@Valid @PathVariable Long resultId, String link) {
         return scoreSender.scoreSender(resultId, link);
    }
}

