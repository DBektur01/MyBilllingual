package javaB13.dto.requests.questions.select_the_main_idea;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import javaB13.dto.requests.option.OptionRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelectTheMainIdeaQuestionRequest {
    private String title;
    private Integer duration;
    private Integer questionOrder;
    private String passage;
    @Valid
    private List<OptionRequest> options;
    private Long testId;
    @NotNull(message = "The is active must not be empty.")
    private Boolean isActive;
}
