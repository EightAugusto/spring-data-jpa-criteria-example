package com.eightaugusto.spring.data.jpa.criteria.example.userlog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;

/** <code>UserLog</code> is an entity class which represents a table using JPA as ORM. */
@Data
@Entity
@Table(name = "user_log")
public class UserLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "timestamp", nullable = false)
  private Instant timestamp;

  @Column(name = "detail", nullable = false)
  private String detail;
}
