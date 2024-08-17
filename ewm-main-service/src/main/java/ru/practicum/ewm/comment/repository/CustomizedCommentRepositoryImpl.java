package ru.practicum.ewm.comment.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import ru.practicum.ewm.comment.model.Comment;
import ru.practicum.ewm.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomizedCommentRepositoryImpl implements CustomizedCommentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAllByAdminParams(Long[] users, Long[] events, LocalDateTime rangeStart,
                                              LocalDateTime rangeEnd, Pageable page) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
        Root<Comment> root = cq.from(Comment.class);

        List<Predicate> predicates = new ArrayList<>();

        if (users != null && users.length > 0) {
            predicates.add(root.get("author").in((Object[]) users));
        }

        if (events != null && events.length > 0) {
            predicates.add(root.get("event").in((Object[]) events));
        }

        if (rangeStart == null && rangeEnd == null) {
            predicates.add(cb.greaterThan(root.get("createdOn"), Util.now()));
        }

        if (rangeStart != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdOn"), rangeStart));
        }

        if (rangeEnd != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdOn"), rangeEnd));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        cq.orderBy(QueryUtils.toOrders(page.getSort(), root, cb));

        List<Comment> comments = entityManager.createQuery(cq)
                .setMaxResults(page.getPageSize())
                .getResultList();

        return comments;
    }
}
