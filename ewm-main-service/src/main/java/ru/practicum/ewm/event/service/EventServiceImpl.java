package ru.practicum.ewm.event.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.category.model.Category;
import ru.practicum.ewm.category.repository.CategoryRepository;
import ru.practicum.ewm.event.dto.NewEventDto;
import ru.practicum.ewm.event.mapper.EventMapper;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.exception.ResourceNotFoundException;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Event addEvent(Long userId, NewEventDto newEventDto) {
        log.debug("Обработка запроса на добавление события {}", newEventDto.toString());
        // TODO: get user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(User.class, userId));
        // TODO: get category
        Category category = categoryRepository.findById(newEventDto.getCategory())
                .orElseThrow(() -> new ResourceNotFoundException(Category.class, newEventDto.getCategory()));
        // TODO: add it all to the event
        Event eventToSave = EventMapper.toEvent(newEventDto, category, user);
        return eventRepository.save(eventToSave);
    }
}
