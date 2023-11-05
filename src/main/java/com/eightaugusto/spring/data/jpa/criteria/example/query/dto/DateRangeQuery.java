package com.eightaugusto.spring.data.jpa.criteria.example.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <code>DateRangeQuery</code> implement the marker interface <code>Query</code> allowing us to use
 * the <code>DateRangeQuerySpecification</code> in our custom <code>AbstractSpecificationFactory
 * </code> implementations for date filtering <code>Specification</code>.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateRangeQuery implements Query {

  @Schema(description = "Timestamp", example = "2024-01-01T00:00:00.000Z")
  private Instant from;

  @Schema(description = "Timestamp", example = "2024-01-01T10:00:00.000Z")
  private Instant to;
}
