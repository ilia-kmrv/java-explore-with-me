package ru.practicum.ewm.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.repository.EventRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Event addEvent(Long userId, Event event) {
        log.debug("Обработка запроса на добавление события {}", event.toString());
        // TODO: get user
        // TODO: get category
        // TODO: add it all to the event
        Event eventToSave = event.toBuilder().build();
        return eventRepository.save(eventToSave);
    }
}
