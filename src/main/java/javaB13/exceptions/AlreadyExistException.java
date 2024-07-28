package javaB13.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {

    }

    public AlreadyExistException(String massage) {
        super(massage);
    }
}
