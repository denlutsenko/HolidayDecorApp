package ua.com.hdcorp.hd.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    private String description;

    CustomException(String message, String description) {
        super(message);
        this.description = description;
    }

    public abstract HttpStatus getStatus();

    public String getDescription() {
        return description;
    }
}