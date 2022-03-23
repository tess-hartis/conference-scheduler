package com.example.conferencescheduler.repositories;

import com.example.conferencescheduler.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long>, SessionRepositoryCustom {
}
