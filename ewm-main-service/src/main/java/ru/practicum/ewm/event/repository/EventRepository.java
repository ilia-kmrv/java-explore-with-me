package ru.practicum.ewm.event.repository;

import org.springframework.data.repository.CrudRepository;
import ru.practicum.ewm.event.model.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
}
