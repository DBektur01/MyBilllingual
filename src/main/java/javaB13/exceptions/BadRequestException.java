package javaB13.exceptions;

import jakarta.mail.MessagingException;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {

    }

    public BadRequestException(String massage) {
        super(massage);
    }

    public BadRequestException(String failedToSendEmail, MessagingException e) {
    }
}
