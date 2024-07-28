package javaB13.dto.responses.userResult;


import javaB13.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class MyResultResponse {
    private Long id;
    private LocalDate dateOfSubmission;
    private String testName;
    private ResultStatus resultStatus;
    private float score;
}
