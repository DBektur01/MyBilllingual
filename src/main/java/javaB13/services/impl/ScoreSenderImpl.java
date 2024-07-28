package javaB13.services.impl;

import  javaB13.dto.responses.SimpleResponse;
import  javaB13.entity.Result;
import  javaB13.entity.Test;
import  javaB13.entity.UserInfo;
import  javaB13.exceptions.NotFoundException;
import  javaB13.repositories.ResultRepository;
import  javaB13.repositories.TestRepository;
import  javaB13.repositories.UserInfoRepository;
import  javaB13.services.EmailService;
import  javaB13.services.ScoreSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScoreSenderImpl implements ScoreSender {
    private final TestRepository testRepository;
    private final TemplateEngine templateEngine;
    private final EmailService emailService;
    private final ResultRepository resultRepository;
    private final UserInfoRepository userInfoRepository;

    @Override
    public SimpleResponse scoreSender(Long resultId, String link) {
        log.info("Initiating password reset");
        try {
            Result result = resultRepository.findById(resultId)
                    .orElseThrow(() -> new NotFoundException(String.format("Result with ID %d not found", resultId)));

            UserInfo userInfo = userInfoRepository.findByUserId(result.getUser().getId())
                    .orElseThrow(()-> new NotFoundException(String.format("User info with ID %d not found", result.getUser().getId())));

            Test test = testRepository.findById(result.getTest().getId())
                    .orElseThrow(() -> new NotFoundException(String.format("Test with ID %d not found", result.getTest().getId())));

            String subject = "Your result";

            Context context = new Context();
            context.setVariable("name",String.format("Hi, %s!", userInfo.getUser().getFirstName()));
            context.setVariable("title",String.format("Your test %s has been successfully verified by the administrator.", test.getTitle()) );
            context.setVariable("link", String.format(link));

            String htmlContent = templateEngine.process("result.html", context);

            emailService.sendEmail(userInfo.getEmail(), subject, htmlContent);
            log.info("score sender");

            return SimpleResponse.builder()
                    .message("The score was sent to your email. Please check your email.")
                    .build();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

