package com.example.conferencescheduler.repositories;

import com.example.conferencescheduler.domain.Speaker;
import io.vavr.control.Option;

public interface SpeakerRepositoryCustom {

    Option<Speaker> findByIdOption (Long id);
    Integer deleteByIdInteger (Long id);
}
