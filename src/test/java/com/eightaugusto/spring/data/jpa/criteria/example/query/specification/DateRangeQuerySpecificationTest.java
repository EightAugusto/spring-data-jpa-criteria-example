package com.eightaugusto.spring.data.jpa.criteria.example.query.specification;

import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.DateRangeQuery;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** DateRangeQuerySpecificationTest. */
public class DateRangeQuerySpecificationTest {

  @Test
  @DisplayName("When create invalid DateRangeQuerySpecification expect IllegalArgumentException")
  public void
      when_create_invalid_date_range_query_specification_expect_illegal_argument_exception() {
    Assertions.assertThrows(
        IllegalArgumentException.class, () -> new DateRangeQuerySpecification<>(null, null));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () ->
            new DateRangeQuerySpecification<>(
                null, root -> root.get(UUID.randomUUID().toString())));
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new DateRangeQuerySpecification<>(new DateRangeQuery(), null));
  }

  @Test
  @DisplayName("When create invalid DateRange expect IllegalArgumentException")
  public void when_create_invalid_date_range_expect_illegal_argument_exception() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () ->
            new DateRangeQuerySpecification<>(
                new DateRangeQuery(), root -> root.get(UUID.randomUUID().toString())));
  }
}
