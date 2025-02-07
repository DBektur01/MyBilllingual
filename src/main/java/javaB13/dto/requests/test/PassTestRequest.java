package javaB13.dto.requests.test;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import javaB13.dto.requests.answer.AnswerRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PassTestRequest {

    @NotNull(message = "Test ID can not be null")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "Answers can not be null")
    @Valid
    private List<AnswerRequest> answers;
}