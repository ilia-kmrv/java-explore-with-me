package ru.practicum.ewm.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handle(final MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Incorrectly made request")
                .status(String.valueOf(HttpStatus.BAD_REQUEST))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handle(final ResourceNotFoundException e) {
        log.warn(e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Resource not found")
                .status(String.valueOf(HttpStatus.NOT_FOUND))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handle(final ResourceAlreadyExistsException e) {
        log.warn(e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Conflict data")
                .status(String.valueOf(HttpStatus.CONFLICT))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handle(final ResourceValidationException e) {
        log.warn(e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Validation error")
                .status(String.valueOf(HttpStatus.BAD_REQUEST))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiError handle(final PermissionDeniedException e) {
        log.warn(e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Access denied")
                .status(String.valueOf(HttpStatus.FORBIDDEN))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handle(final Throwable e) {
        log.warn(e.getMessage());
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Internal server error")
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR))
                .timestamp(LocalDateTime.now())
                .build();
    }
}
