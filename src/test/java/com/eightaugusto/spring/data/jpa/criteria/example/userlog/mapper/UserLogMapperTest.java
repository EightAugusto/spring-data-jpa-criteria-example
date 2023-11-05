package com.eightaugusto.spring.data.jpa.criteria.example.userlog.mapper;

import com.eightaugusto.spring.data.jpa.criteria.example.common.factory.CommonFactory;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogDto;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.entity.UserLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** UserLogMapperTest. */
public class UserLogMapperTest {

  public static UserLogMapper MAPPER = new UserLogMapperImpl();

  @Test
  @DisplayName("When map null dto expect null mapped entity")
  public void when_map_null_dto_expect_null_mapped_entity() {
    Assertions.assertNull(MAPPER.convert((UserLogDto) null));
    Assertions.assertNull(MAPPER.convert((UserLog) null));
  }

  @Test
  @DisplayName("When map dto expect mapped entity")
  public void when_map_dto_expect_mapped_entity() {
    final var dto = CommonFactory.readClassFromResource(UserLogDto.class);

    assertDtoAndEntity(dto, MAPPER.convert(dto));
  }

  @Test
  @DisplayName("When map entity expect mapped dto")
  public void when_map_entity_expect_mapped_dto() {
    final var entity = CommonFactory.readClassFromResource(UserLog.class);

    assertDtoAndEntity(MAPPER.convert(entity), entity);
  }

  public static void assertDtoAndEntity(UserLogDto dto, UserLog entity) {
    Assertions.assertEquals(dto.getId(), entity.getId());
    Assertions.assertEquals(dto.getUserId(), entity.getUserId());
    Assertions.assertEquals(dto.getTimestamp(), entity.getTimestamp());
    Assertions.assertEquals(dto.getDetail(), entity.getDetail());
  }
}
