package javaB13.dto.responses.result;
import javaB13.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class SubmittedResultsResponse {
    private Long resultId;
    private String userFullName;
    private LocalDateTime dateOfSubmission;
    private String testName;
    private ResultStatus status;
    private float finalScore;
}
