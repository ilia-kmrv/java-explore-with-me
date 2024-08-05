package ru.practicum.ewm.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Util {
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime toLocalDateTime(String dateString) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public static String toStringTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public static Pageable page(int from, int size) {
        return PageRequest.of(from > 0 ? from / size : 0, size);
    }
}
