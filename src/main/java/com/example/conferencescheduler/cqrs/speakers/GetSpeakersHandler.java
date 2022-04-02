package com.example.conferencescheduler.cqrs.speakers;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetSpeakersHandler {

    private final SpeakerRepository speakerRepository;

    public List<Speaker> handle(GetSpeakersQuery request){ return speakerRepository.findAll();}
}
