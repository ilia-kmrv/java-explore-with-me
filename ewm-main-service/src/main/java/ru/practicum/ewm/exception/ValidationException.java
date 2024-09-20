package ru.practicum.ewm.exception;

public class ValidationException extends RuntimeException {
    public ValidationException() {

    }

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final String message, Throwable cause) {
        super(message, cause);
    }
}
