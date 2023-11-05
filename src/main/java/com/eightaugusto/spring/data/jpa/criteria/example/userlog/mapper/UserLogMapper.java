package com.eightaugusto.spring.data.jpa.criteria.example.userlog.mapper;

import com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto.UserLogDto;
import com.eightaugusto.spring.data.jpa.criteria.example.userlog.entity.UserLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

/**
 * UserLogMapper use <code>Mapping</code> annotation from Mapstruct core to be identified by the
 * processor. This will generate an implementation of this interface and define the non-implemented
 * functions which mapping code, additionally, <code>@Mapper(componentModel = ComponentModel.SPRING)
 * </code> will annotate this implementation with <code>@Component</code> allowing Spring Component
 * Scan detection.
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface UserLogMapper {

  @Mapping(target = "id", ignore = true)
  UserLog convert(UserLogDto source);

  UserLogDto convert(UserLog source);
}
