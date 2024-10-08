package ru.practicum.stats.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ViewStats {
    private String app;

    private String uri;

    private Long hits;
}
