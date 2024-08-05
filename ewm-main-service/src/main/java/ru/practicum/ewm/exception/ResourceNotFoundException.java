package ru.practicum.ewm.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public <T> ResourceNotFoundException(Class<T> tClass, Long id) {
        super(String.format("%s c id=%d не найден", tClass.getSimpleName(), id));
    }

    public ResourceNotFoundException(final String message, Throwable cause) {
        super(message, cause);
    }
}
