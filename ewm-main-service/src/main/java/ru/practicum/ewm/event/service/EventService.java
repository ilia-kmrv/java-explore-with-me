package ru.practicum.ewm.event.service;

import ru.practicum.ewm.event.model.Event;

public interface EventService {
    Event addEvent(Long userId, Event event);
}
