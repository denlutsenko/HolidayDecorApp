package ua.com.hdcorp.hd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public enum Message {
        ROLE_NOT_FOUND,
        EMPLOYEE_NOT_FOUND
    }

    public NotFoundException(Message message) {
        super(message.name());
    }
}