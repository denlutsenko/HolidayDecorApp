package ua.com.hdcorp.hd.web.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.hdcorp.hd.error.Error;
import ua.com.hdcorp.hd.error.ErrorSource;
import ua.com.hdcorp.hd.exception.CustomException;
import ua.com.hdcorp.hd.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({CustomException.class,ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(CustomException customException,ConstraintViolationException exception ) {

        Error error = new Error(customException.getStatus().value(), exception.getMessage(), customException.getDescription());
        return ResponseEntity.status(customException.getStatus()).body(error);
    }
}
