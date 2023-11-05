package com.eightaugusto.spring.data.jpa.criteria.example.userlog.controller;

import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogDto;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.service.UserLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** UserLogController is the entrypoint the final destination point that a web request can reach. */
@Log4j2
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/userlog")
@Tags({@Tag(name = "User Log")})
public class UserLogController {

  private final UserLogService service;

  /**
   * Entrypoint for post an UserLog.
   *
   * @param dto UserLogDto.
   */
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public void post(@RequestBody @Valid UserLogDto dto) {
    log.traceEntry("({})", dto);
    service.post(dto);
    log.traceExit();
  }

  /**
   * Entrypoint for get UserLog by Query and Pageable.
   *
   * @param pageable Pageable.
   * @return UserLog page.
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Page<UserLogDto> getByQueryAndPageable(
      @ParameterObject UserLogQuery query, @ParameterObject Pageable pageable) {
    log.traceEntry("({}, {})", query, pageable);
    final Page<UserLogDto> page = service.getByQueryAndPageable(query, pageable);
    log.traceExit();
    return page;
  }
}
