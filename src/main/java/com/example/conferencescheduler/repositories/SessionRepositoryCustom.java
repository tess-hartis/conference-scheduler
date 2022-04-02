package com.example.conferencescheduler.repositories;

import com.example.conferencescheduler.domain.Session;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepositoryCustom {

    Option<Session> findByIdOption(Long id);
    Integer deleteByIdCustom(Long id);
}
