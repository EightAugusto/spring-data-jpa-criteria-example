package com.eightaugusto.spring.data.jpa.criteria.example.userlog.service.impl;

import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogDto;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.mapper.UserLogMapper;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.repository.UserLogRepository;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.service.UserLogService;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.specification.UserLogSpecificationFactory;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/** UserLogServiceImpl. */
@Log4j2
@Service
@AllArgsConstructor
public class UserLogServiceImpl implements UserLogService {

  private final UserLogMapper mapper;
  private final UserLogRepository repository;
  private final UserLogSpecificationFactory specificationFactory;

  @Override
  public void post(UserLogDto dto) {
    log.traceEntry("({})", dto);
    repository.save(mapper.convert(dto));
    log.traceExit();
  }

  @Override
  public Page<UserLogDto> getByQueryAndPageable(UserLogQuery query, Pageable pageable) {
    log.traceEntry("({}, {})", query, pageable);
    final Page<UserLogDto> page =
        repository.findAll(specificationFactory.build(query), pageable).map(mapper::convert);
    log.traceExit();
    return page;
  }
}
