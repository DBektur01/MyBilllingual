package javaB13.exceptions;

public class BadCredentialException extends RuntimeException {
    public BadCredentialException() {

    }

    public BadCredentialException(String massage) {
        super(massage);
    }
}
