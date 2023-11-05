package com.eightaugusto.spring.data.jpa.criteria.example.userlog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.Data;

/** UserLog data transformation object (DTO). */
@Data
public class UserLogDto {

  @Schema(accessMode = AccessMode.READ_ONLY, description = "User log id", example = "1")
  private Long id;

  @NotBlank
  @Schema(description = "User id", example = "EightAugusto")
  private String userId;

  @NotNull
  @Schema(description = "Timestamp", example = "2024-01-01T00:00:00.000Z")
  private Instant timestamp;

  @NotBlank
  @Schema(description = "User log description", example = "Log created")
  private String detail;
}
