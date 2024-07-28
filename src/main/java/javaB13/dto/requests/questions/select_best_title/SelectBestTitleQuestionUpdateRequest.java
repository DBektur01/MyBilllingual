package javaB13.dto.requests.questions.select_best_title;

import jakarta.validation.Valid;
import javaB13.dto.requests.option.OptionUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class SelectBestTitleQuestionUpdateRequest {
    private String title;
    private Integer duration;
    private Integer questionOrder;
    private String passage;
    @Valid
    private List<OptionUpdateRequest> options;
    private Boolean isActive;

    public SelectBestTitleQuestionUpdateRequest() {}
}
