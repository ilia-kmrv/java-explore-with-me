package ru.practicum.ewm.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.mapper.EventMapper;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.service.EventService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PrivateEventController {

    private final EventService eventService;

    @PostMapping
    public EventFullDto postEvent(@PathVariable Long userId, @Valid @RequestBody NewEventDto newEventDto) {
        log.info("Получен запрос на добавление события {} от пользователя с id={}", newEventDto.toString(), userId);
        Event event = eventService.addEvent(userId, newEventDto);
        return EventMapper.toEventFullDto(event);
    }
}
