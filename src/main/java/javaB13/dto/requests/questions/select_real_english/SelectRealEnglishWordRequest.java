package javaB13.dto.requests.questions.select_real_english;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import javaB13.dto.requests.option.OptionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
public class SelectRealEnglishWordRequest {
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The question order must not be empty.")
    @Positive(message = "Test question order can not be negative")
    private Integer questionOrder;
    @NotNull(message = "The is active must not be empty.")
    private Boolean isActive;
    @NotNull(message = "The test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "The option must not be empty.")
    @Valid
    private List<OptionRequest> options;

    public SelectRealEnglishWordRequest() {
    }

}
