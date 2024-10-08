package ru.practicum.ewm.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.practicum.ewm.user.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByIdIn(List<Long> userIds);

    List<User> findAllBy(Pageable page);
}
