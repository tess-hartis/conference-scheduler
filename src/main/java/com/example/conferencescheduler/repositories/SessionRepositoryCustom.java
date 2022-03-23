package com.example.conferencescheduler.repositories;

import com.example.conferencescheduler.domain.Session;
import io.vavr.control.Option;

public interface SessionRepositoryCustom {

    Option<Session> findByIdOption(Long id);
    Integer deleteByIdOption(Long id);
}
