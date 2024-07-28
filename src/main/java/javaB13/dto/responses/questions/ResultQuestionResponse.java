package javaB13.dto.responses.questions;

import javaB13.dto.responses.answer.UserAnswerResponse;
import javaB13.enums.AnswerStatus;
import javaB13.enums.QuestionType;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultQuestionResponse {
    private Long questionId;
    private QuestionType questionType;
    private float score;
    private AnswerStatus answerStatus;
    private List<UserAnswerResponse> userAnswerResponse;
    private Integer questionOrder;

    public ResultQuestionResponse(Long questionId, QuestionType questionType, float score, Integer questionOrder) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.score = score;
        this.questionOrder = questionOrder;
    }
}
