package ru.practicum.ewm.comment.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CommentShortDto {

    private Long id;

    private String authorName;

    private LocalDateTime createdOn;

    private LocalDateTime edited;

    private Long eventId;

    private String text;


}
