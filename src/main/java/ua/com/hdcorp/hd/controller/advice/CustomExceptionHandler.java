package ua.com.hdcorp.hd.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.com.hdcorp.hd.error.Error;
import ua.com.hdcorp.hd.error.ErrorSource;
import ua.com.hdcorp.hd.exception.BadRequestException;
import ua.com.hdcorp.hd.exception.CustomException;

import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {
        Set<Error> errors = exception.getConstraintViolations().stream().map(constraintViolation ->
                Error.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .source(ErrorSource.builder()
                                .pointer(constructPointer(constraintViolation.getPropertyPath()))
                                .parameter(constraintViolation.getInvalidValue().toString())
                                .build())
                        .title(BadRequestException.Message.VALIDATION_ERROR.name())
                        .detail(constraintViolation.getMessage())
                        .build()).collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleBadRequest(CustomException exception) {
        Error error = Error.builder()
                .status(exception.getStatus().value())
                .title(exception.getMessage())
                .detail(exception.getDescription())
                .build();
        return ResponseEntity.status(exception.getStatus()).body(error);
    }

    private String constructPointer(Path path) {
        List<String> jsonPath = new ArrayList<>();
        path.forEach(pathPart -> {
            if (ElementKind.PROPERTY.equals(pathPart.getKind())) {
                jsonPath.add(pathPart.toString());
            }
        });
        return String.join(".", jsonPath);
    }
}