package javaB13.services.questions;

import javaB13.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import javaB13.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import javaB13.dto.responses.SimpleResponse;

public interface DescribeImageQuestionService {
    SimpleResponse saveDescribeQuestion(DescribeImageQuestionRequest request);

    SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest);
}
