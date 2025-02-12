package javaB13.dto.requests.questions.select_real_english;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import javaB13.dto.requests.option.OptionUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SelectRealEnglishWordUpdateRequest {
    private String title;
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    private Integer questionOrder;
    private Boolean isActive;
    @NotNull(message = "The Option must not be empty.")
    @Valid
    private List<OptionUpdateRequest> options;

    public SelectRealEnglishWordUpdateRequest() {
    }
}
