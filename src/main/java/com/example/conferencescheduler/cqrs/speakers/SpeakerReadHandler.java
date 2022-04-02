package com.example.conferencescheduler.cqrs.speakers;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SpeakerReadHandler {

    private final SpeakerRepository speakerRepository;

    public Option<Speaker> handleGetOne(GetSpeakerQuery request){ return speakerRepository.findByIdOption(request.id);}

    public List<Speaker> handleGetAll(GetSpeakersQuery request){ return speakerRepository.findAll();}
}
