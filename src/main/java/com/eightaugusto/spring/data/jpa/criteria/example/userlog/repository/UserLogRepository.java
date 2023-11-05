package com.eightaugusto.spring.data.jpa.criteria.example.userlog.repository;

import com.eightaugusto.spring.data.jpa.criteria.example.userlog.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * <code>UserLogRepository</code> implements <code>JpaRepository</code> and <code>
 * JpaSpecificationExecutor</code> with the entity <code>UserLog</code> enable basic CRUD operations
 * and Specification operations.
 */
@Repository
public interface UserLogRepository
    extends JpaRepository<UserLog, Long>, JpaSpecificationExecutor<UserLog> {}
