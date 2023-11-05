package com.eightaugusto.spring.data.jpa.criteria.example.userlog.service;

import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogDto;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** UserLogService. */
public interface UserLogService {

  /**
   * Post UserLog.
   *
   * @param dto UserLog.
   */
  void post(UserLogDto dto);

  /**
   * Get UserLog by Query and Pageable.
   *
   * @param pageable Pageable.
   * @return UserLog page.
   */
  Page<UserLogDto> getByQueryAndPageable(UserLogQuery query, Pageable pageable);
}
