--liquibase formatted sql

--changeset user_log:eightaugusto
CREATE TABLE IF NOT EXISTS `user_log`
  (
     `id`        BIGINT auto_increment NOT NULL,
     `user_id`   VARCHAR(128) NOT NULL,
     `timestamp` DATETIME NOT NULL,
     `detail`    TEXT NOT NULL
  );