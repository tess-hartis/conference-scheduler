package com.conferencescheduler.repositories;

import com.conferencescheduler.domain.Speaker;
import io.vavr.control.Option;

public interface SpeakerRepositoryCustom {

    Option<Speaker> findByIdOption (Long id);
    Integer deleteByIdCustom(Long id);
}
