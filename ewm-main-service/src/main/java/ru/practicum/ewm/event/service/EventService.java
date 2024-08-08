package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.model.Event;

public interface EventService {
    Event addEvent(Long userId, NewEventDto newEventDto);
}
