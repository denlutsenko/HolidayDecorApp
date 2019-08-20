package ua.com.hdcorp.hd.exception;

import org.springframework.http.HttpStatus;

public class CreationException extends CustomException {

    public enum Message {
        PHOTO_SAVING_ERROR,
        FOLDER_CREATION_ERROR
    }

    public CreationException(Message message, String description) {
        super(message.name(), description);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}