package javaB13.dto.responses.questions;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import javaB13.dto.responses.file.FileResponse;
import javaB13.dto.responses.option.OptionResponse;
import javaB13.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private Long id;
    private String title;
    private String statement;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = QuestionType.DESCRIBE_IMAGE;
    private Integer duration;
    private Integer minWords;
    private Integer numberOfReplays;
    private String correctAnswer;
    private String passage;
    private Integer questionOrder;
    private String audioText;
    private Long testId;
    private List<FileResponse> files;
    private List<OptionResponse> options;
    private Boolean isActive;
}
