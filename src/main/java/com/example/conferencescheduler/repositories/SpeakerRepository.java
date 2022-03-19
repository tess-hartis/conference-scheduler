package com.example.conferencescheduler.repositories;

import com.example.conferencescheduler.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
