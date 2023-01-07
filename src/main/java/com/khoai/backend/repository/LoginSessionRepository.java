package com.khoai.backend.repository;

import com.khoai.backend.model.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Integer> {
    Optional<LoginSession> findByToken(String token);
}
