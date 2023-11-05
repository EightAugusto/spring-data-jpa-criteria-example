package com.eightaugusto.spring.data.jpa.criteria.example.userlog.specification;

import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.DateRangeQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.query.specification.AbstractSpecificationFactory;
import com.eightaugusto.spring.data.jpa.criteria.example.query.specification.DateRangeQuerySpecification;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.entity.UserLog;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.entity.UserLog_;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * <code>UserLogSpecificationFactory</code> implements <code>AbstractSpecificationFactory</code>
 * using the entity <code>UserLog</code> with <code>UserLogQuery</code> creating optional filters
 * for the specification.
 */
@Service
public class UserLogSpecificationFactory
    extends AbstractSpecificationFactory<UserLog, UserLogQuery> {

  @Override
  protected List<Specification<UserLog>> getSpecificationByQuery(UserLogQuery query) {
    final List<Specification<UserLog>> specifications = new LinkedList<>();

    Optional.ofNullable(query.getDate())
        .map(this::getDateRangeQuerySpecification)
        .ifPresent(specifications::add);
    Optional.ofNullable(query.getUserIds())
        .filter(userIds -> !userIds.isEmpty())
        .map(this::getUserIdsSpecification)
        .ifPresent(specifications::add);

    return specifications;
  }

  private Specification<UserLog> getDateRangeQuerySpecification(DateRangeQuery dateRangeQuery) {
    return new DateRangeQuerySpecification<>(dateRangeQuery, root -> root.get(UserLog_.TIMESTAMP));
  }

  private Specification<UserLog> getUserIdsSpecification(Set<String> userIds) {
    return (root, query, builder) -> builder.in(root.get(UserLog_.USER_ID)).value(userIds);
  }
}
