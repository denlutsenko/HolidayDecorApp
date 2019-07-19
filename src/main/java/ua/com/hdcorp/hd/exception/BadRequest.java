package ua.com.hdcorp.hd.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequest extends RuntimeException {

    public enum Message{
        ROLE_ALREADY_EXISTS
    }
    public BadRequest(Message message) {
        super(message.name());
    }
}
