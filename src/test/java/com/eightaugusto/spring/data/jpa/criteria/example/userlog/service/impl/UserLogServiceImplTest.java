package com.eightaugusto.spring.data.jpa.criteria.example.userlog.service.impl;

import com.eightaugusto.spring.data.jpa.criteria.example.common.factory.CommonFactory;
import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.DateRangeQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogDto;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.mapper.UserLogMapperTest;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.repository.UserLogRepository;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.service.UserLogService;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.specification.UserLogSpecificationFactory;
import java.time.Duration;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

/**
 * <code>UserLogServiceImplTest</code> will test <code>UserLogServiceImpl</code>; <code>DataJpaTest
 * </code> Instruct required beans creation like <code>DataSource</code>, <code>JdbcTemplate</code>,
 * <code>EntityManager</code> and <code>UserLogRepository</code>.
 */
@DataJpaTest
public class UserLogServiceImplTest {

  private final UserLogService service;

  @Autowired
  public UserLogServiceImplTest(UserLogRepository repository) {
    service =
        new UserLogServiceImpl(
            UserLogMapperTest.MAPPER, repository, new UserLogSpecificationFactory());
  }

  @Test
  @DisplayName("When save entity and getByQueryAndPageable expect dto")
  public void when_save_entity_and_get_by_query_and_pageable_expect_dto() {
    final var dto = CommonFactory.readClassFromResource(UserLogDto.class);
    service.post(dto);

    final var page =
        service.getByQueryAndPageable(
            UserLogQuery.builder().userId(dto.getUserId()).build(), Pageable.unpaged());

    Assertions.assertEquals(1, page.getSize());
  }

  @Test
  @DisplayName("When save entity and getByQueryAndPageable with invalid userId expect empty")
  public void when_save_entity_and_get_by_query_and_pageable_with_invalid_user_id_expect_empty() {
    service.post(CommonFactory.readClassFromResource(UserLogDto.class));

    final var page =
        service.getByQueryAndPageable(
            UserLogQuery.builder().userId(UUID.randomUUID().toString()).build(),
            Pageable.unpaged());

    Assertions.assertEquals(0, page.getSize());
  }

  @Test
  @DisplayName("When save one entity and getByQueryAndPageable with different dates")
  public void when_save_one_entity_and_get_by_query_and_pageable_with_different_dates() {
    final var dto = CommonFactory.readClassFromResource(UserLogDto.class);
    service.post(dto);

    final var dateRangeQuery = DateRangeQuery.builder().build();
    final var query = UserLogQuery.builder().date(dateRangeQuery).build();

    dateRangeQuery.setFrom(dto.getTimestamp());
    dateRangeQuery.setTo(null);
    Assertions.assertEquals(1, service.getByQueryAndPageable(query, Pageable.unpaged()).getSize());

    dateRangeQuery.setFrom(dto.getTimestamp().plus(Duration.ofMinutes(1L)));
    dateRangeQuery.setTo(null);
    Assertions.assertEquals(0, service.getByQueryAndPageable(query, Pageable.unpaged()).getSize());

    dateRangeQuery.setFrom(null);
    dateRangeQuery.setTo(dto.getTimestamp());
    Assertions.assertEquals(1, service.getByQueryAndPageable(query, Pageable.unpaged()).getSize());

    dateRangeQuery.setFrom(null);
    dateRangeQuery.setTo(dto.getTimestamp().minus(Duration.ofMinutes(1L)));
    Assertions.assertEquals(0, service.getByQueryAndPageable(query, Pageable.unpaged()).getSize());

    dateRangeQuery.setFrom(dto.getTimestamp());
    dateRangeQuery.setTo(dto.getTimestamp());
    Assertions.assertEquals(1, service.getByQueryAndPageable(query, Pageable.unpaged()).getSize());
  }
}
