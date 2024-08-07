package ru.practicum.ewm.request.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.event.model.EventState;
import ru.practicum.ewm.event.repository.EventRepository;
import ru.practicum.ewm.exception.ConflictException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.request.dto.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.request.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.request.mapper.RequestMapper;
import ru.practicum.ewm.request.model.Request;
import ru.practicum.ewm.request.model.RequestStatus;
import ru.practicum.ewm.request.repository.RequestRepository;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Request addRequest(Long userId, Long eventId) {
        log.debug("Обработка добавления запроса на участие в событии id={} от пользователя id={}", userId, eventId);
        User requester = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(Event.class, eventId));

        if (requestRepository.findByRequesterIdAndEventId(userId, eventId).isPresent()) {
            throw new ConflictException("Запрос на участие уже существует");
        }

        if (event.getInitiator().getId() == userId) {
            throw new ConflictException("Инициатор события не может оставить запрос на участие");
        }

        if (event.getState() != EventState.PUBLISHED) {
            throw new ConflictException("Участвовать можно только в опубликованном событии");
        }

        if (event.getParticipantLimit() != 0 && (event.getParticipantLimit() - event.getConfirmedRequests()) <= 0) {
            throw new ConflictException("Лимит участников превышен");
        }

        boolean hasNoLimitOrModeration = !event.getRequestModeration() || event.getParticipantLimit() == 0;

        Request request = Request.builder()
                .event(event)
                .requester(requester)
                .status(hasNoLimitOrModeration ? RequestStatus.CONFIRMED : RequestStatus.PENDING)
                .build();

        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAllUserRequests(Long userId) {
        log.debug("Обработка просмотра всех запросов на участие пользователя с id={}", userId);
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId));
        return requestRepository.findAllByRequesterId(userId);
    }

    @Override
    public List<Request> getAllEventRequestsByInitiator(Long userId, Long eventId) {
        log.debug("Обработка запроса на просмотр заявок на участие в событии id={} пользователя id={}", eventId, userId);
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(Event.class, eventId));
        if (event.getInitiator().getId() != userId) {
            throw new NotFoundException(Event.class, eventId);
        }
        return requestRepository.findAllByEventId(eventId);
    }

    @Override
    public EventRequestStatusUpdateResult updateRequestsByInitiator(Long userId,
                                                                    Long eventId,
                                                                    EventRequestStatusUpdateRequest update) {
        log.debug("Обработка запроса на изменение заявок события id={} пользователя id={}", eventId, userId);
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(Event.class, eventId));

        if (event.getInitiator().getId() != userId) {
            throw new NotFoundException(Event.class, eventId);
        }

        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            return EventRequestStatusUpdateResult.builder()
                    .confirmedRequests(Collections.emptyList())
                    .rejectedRequests(Collections.emptyList())
                    .build();
        }

        List<Long> requestIds = update.getRequestIds();

        List<Request> requests = requestRepository.findAllByStatusAndIdIn(RequestStatus.PENDING, requestIds);

        List<Request> confirmedRequests = new ArrayList<>();
        List<Request> rejectedRequests = new ArrayList<>();

        long placesLeft = event.getParticipantLimit() - event.getConfirmedRequests();

        for (Request request : requests) {
           if (placesLeft > 0 && update.getStatus() == RequestStatus.CONFIRMED) {
               request.setStatus(RequestStatus.CONFIRMED);
               confirmedRequests.add(request);
               placesLeft--;
           } else {
               request.setStatus(RequestStatus.REJECTED);
               rejectedRequests.add(request);
           }
        }

        requestRepository.saveAll(requests);

        return EventRequestStatusUpdateResult.builder()
                .confirmedRequests(RequestMapper.toDtoList(confirmedRequests))
                .rejectedRequests(RequestMapper.toDtoList(rejectedRequests))
                .build();
    }

    @Override
    public Request cancelRequestByUser(Long userId, Long requestId) {
        log.debug("Обработка отмены участия пользователя id={} по запросу id={}", userId, requestId);
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId));
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException(Request.class, requestId));

        if (request.getRequester().getId() != userId) {
            throw new ConflictException("Отменить участие может только создатель заявки");
        }

        request.setStatus(RequestStatus.CANCELED);

        return requestRepository.save(request);
    }
}
