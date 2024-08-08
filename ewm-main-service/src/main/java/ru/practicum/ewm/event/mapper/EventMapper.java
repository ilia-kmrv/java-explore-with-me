package ru.practicum.ewm.event.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.event.dto.EventFullDto;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.model.EventState;
import ru.practicum.ewm.user.mapper.UserMapper;
import ru.practicum.ewm.user.model.User;

import java.time.LocalDateTime;

@UtilityClass
public class EventMapper {
    public Event toEvent(NewEventDto newEventDto, Category category, User user) {
        return Event.builder()
                .annotation(newEventDto.getAnnotation())
                .category(category)
                .createdOn(LocalDateTime.now())
                .eventDate(newEventDto.getEventDate())
                .description(newEventDto.getDescription())
                .initiator(user)
                .location(newEventDto.getLocation())
                .paid(newEventDto.getPaid())
                .participantLimit(newEventDto.getParticipantLimit())
                .publishedOn(null)
                .requestModeration(newEventDto.getRequestModeration())
                .state(EventState.PENDING)
                .title(newEventDto.getTitle())
                .build();
    }

    public EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(event.getCategory())
                .createdOn(event.getCreatedOn())
                .eventDate(event.getEventDate())
                .description(event.getDescription())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(event.getLocation())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .build();
    }
}
