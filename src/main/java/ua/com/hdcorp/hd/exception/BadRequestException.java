package ua.com.hdcorp.hd.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public enum Message {
        ROLE_ALREADY_EXISTS,
        VALIDATION_ERROR
    }

    public BadRequestException(Message message, String description) {
        super(message.name(), description);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}