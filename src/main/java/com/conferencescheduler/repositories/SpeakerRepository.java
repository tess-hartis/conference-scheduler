package com.conferencescheduler.repositories;

import com.conferencescheduler.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long>, SpeakerRepositoryCustom {
}
