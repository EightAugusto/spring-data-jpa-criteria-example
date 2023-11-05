package com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto;

import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.DateRangeQuery;
import com.eightaugusto.spring.data.jpa.criteria.example.query.dto.Query;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * <code>UserLogQuery</code> use the marker interface <code>Query</code> and place query fields like
 * <code>userId</code> and <code>DateRangeQuery</code>.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLogQuery implements Query {

  @Singular
  @ArraySchema(schema = @Schema(description = "User ids", example = "EightAugusto"))
  private Set<String> userIds;

  private DateRangeQuery date;
}
