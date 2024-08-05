package ru.practicum.ewm.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ApiError {
    private String description;

    private List<String> errors;

    private String message;

    private String reason;

    private String status;

    private LocalDateTime timestamp;
}
