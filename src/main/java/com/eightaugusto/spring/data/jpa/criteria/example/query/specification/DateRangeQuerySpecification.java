package com.eightaugusto.spring.data.jpa.criteria.example.query.specification;

import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.DateRangeQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.Instant;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

/**
 * Create a custom <code>Specification</code> for <code>DateRangeQuery</code> allowing a date filter
 * using from, to or between (fromDate to toDate) date.
 *
 * @param <E> Entity.
 */
public final class DateRangeQuerySpecification<E> implements Specification<E> {

  private final DateRangeQuery query;
  private final Function<Root<E>, Path<Instant>> pathGetter;

  /**
   * Based on the <code>Root</code> and the <code>Function</code> for <code>Path</code> providing
   * create an <code>Specification</code>.
   *
   * @param query Query.
   * @param pathGetter Path getter.
   */
  public DateRangeQuerySpecification(
      @NonNull DateRangeQuery query, @NonNull Function<Root<E>, Path<Instant>> pathGetter) {
    if (Stream.of(query.getFrom(), query.getTo()).allMatch(Objects::isNull)) {
      throw new IllegalArgumentException("At least one of the from/to date should be present");
    }
    this.query = query;
    this.pathGetter = pathGetter;
  }

  @Override
  public Predicate toPredicate(
      Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    final Path<Instant> path = this.pathGetter.apply(root);

    final Instant from = this.query.getFrom();
    final Instant to = this.query.getTo();

    if (from == null || to == null) {
      if (from != null) {
        return criteriaBuilder.greaterThanOrEqualTo(path, from);
      } else {
        return criteriaBuilder.lessThanOrEqualTo(path, to);
      }
    } else {
      return criteriaBuilder.between(path, from, to);
    }
  }
}
