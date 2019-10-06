package ua.com.hdcorp.hd.web;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.hdcorp.hd.exception.FieldValidationException;

import java.util.ArrayList;
import java.util.List;

import static ua.com.hdcorp.hd.utils.Constants.VALIDATION_FAILED;

@ControllerAdvice
public class FieldValidationAdviceController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
        details.add(error.getDefaultMessage());
        });

        return new ResponseEntity<>(new FieldValidationException(VALIDATION_FAILED, details), HttpStatus.BAD_REQUEST);
    }
}
