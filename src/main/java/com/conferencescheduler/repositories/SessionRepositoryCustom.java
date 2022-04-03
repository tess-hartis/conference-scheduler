package com.conferencescheduler.repositories;

import com.conferencescheduler.domain.Session;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepositoryCustom {

    Option<Session> findByIdOption(Long id);
    Integer deleteByIdCustom(Long id);
}
