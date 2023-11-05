package com.eightaugusto.spring.data.jpa.criteria.example.common.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.Set;
import lombok.Data;

/** ErrorDto. */
@Data
public class ErrorDto {

  @Schema(description = "Status code", example = "404")
  private final int status;

  @Schema(description = "Timestamp", example = "2024-01-01T00:00:00.000Z")
  private final Instant timestamp = Instant.now();

  @Schema(description = "Error message", example = "Not found")
  private final String error;

  @ArraySchema(schema = @Schema(description = "Error message", example = "Not found"))
  private final Set<String> messages;
}
