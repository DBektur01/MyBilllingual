package javaB13.services.questions.impl;

import  javaB13.dto.requests.questions.describe_image.DescribeImageQuestionRequest;
import  javaB13.dto.requests.questions.describe_image.DescribeImageQuestionUpdateRequest;
import  javaB13.dto.responses.SimpleResponse;
import  javaB13.entity.*;
import  javaB13.enums.FileType;
import  javaB13.enums.QuestionType;
import  javaB13.exceptions.NotFoundException;
import  javaB13.repositories.QuestionRepository;
import  javaB13.repositories.TestRepository;
import  javaB13.services.questions.DescribeImageQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DescribeImageQuestionServiceImpl implements DescribeImageQuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public SimpleResponse saveDescribeQuestion(DescribeImageQuestionRequest request) {
        log.info("Saving describe image question");
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(String.format("Test with ID %s does not exist!", request.getTestId())));

        Question question = Question.builder()
                .title(request.getTitle())
                .correctAnswer(request.getCorrectAnswer())
                .questionType(QuestionType.DESCRIBE_IMAGE)
                .duration(request.getDuration())
                .test(test)
                .isActive(request.getIsActive())
                .build();

        File file = new File(FileType.IMAGE, request.getFile(), question);
        question.setFiles(List.of(file));
        questionRepository.save(question);

        log.info("Describe image question saved successfully");
        return SimpleResponse.builder()
                .message(String.format("Question with title \"%s\" saved successfully!", request.getTitle()))
                .build();
    }

    @Override
    public SimpleResponse updateDescribeImageQuestionById(Long id, DescribeImageQuestionUpdateRequest updateRequest) {
        log.info("Updating describe image question with ID: {}", id);
        Question question = questionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Question with ID %s does not exist!", id)));

        // Update question fields if provided in the update request
        if (updateRequest.getTitle() != null) {
            question.setTitle(updateRequest.getTitle());
        }
        if (updateRequest.getCorrectAnswer() != null) {
            question.setCorrectAnswer(updateRequest.getCorrectAnswer());
        }
        if (updateRequest.getDuration() != null) {
            question.setDuration(updateRequest.getDuration());
        }
        if (updateRequest.getQuestionOrder() != null) {
            question.setQuestionOrder(updateRequest.getQuestionOrder());
        }
        if (updateRequest.getIsActive() != null) {
            question.setIsActive(updateRequest.getIsActive());
        }

        // Update file URL if provided in the update request
        if (updateRequest.getFile() != null) {
            File file = question.getFiles().get(0);
            file.setFileUrl(updateRequest.getFile());
            List<File>files = new ArrayList<>();
            files.add(file);
            question.setFiles(files);
        }

        questionRepository.save(question);

        log.info("Describe image question with ID {} updated successfully", id);
        return SimpleResponse.builder()
                .message(String.format("Question with ID %s updated successfully!", id))
                .build();
    }
}