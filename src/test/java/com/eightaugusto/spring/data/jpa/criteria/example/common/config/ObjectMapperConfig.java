package com.eightaugusto.spring.data.jpa.criteria.example.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;

/** ObjectMapperConfig. */
@UtilityClass
public class ObjectMapperConfig {

  private static ObjectMapper OBJECT_MAPPER;

  public static ObjectMapper getObjectMapper() {
    if (OBJECT_MAPPER == null) {
      OBJECT_MAPPER = new ObjectMapper();
      OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }
    return OBJECT_MAPPER;
  }
}
