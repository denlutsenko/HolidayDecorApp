package ua.com.hdcorp.hd.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public enum Message {
        ROLE_NOT_FOUND,
        EMPLOYEE_NOT_FOUND,
        POSTCARD_TYPE_NOT_FOUND
    }

    public NotFoundException(Message message, String description) {
        super(message.name(), description);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}