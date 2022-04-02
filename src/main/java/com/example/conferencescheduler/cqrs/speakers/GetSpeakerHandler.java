package com.example.conferencescheduler.cqrs.speakers;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetSpeakerHandler {

    private final SpeakerRepository speakerRepository;

    public Option<Speaker> handle(GetSpeakerQuery request){ return speakerRepository.findByIdOption(request.id);}
}
