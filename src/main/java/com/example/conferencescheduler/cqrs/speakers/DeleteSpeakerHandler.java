package com.example.conferencescheduler.cqrs.speakers;

import com.example.conferencescheduler.repositories.SpeakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteSpeakerHandler {

    private final SpeakerRepository speakerRepository;

    public Integer handle(DeleteSpeakerCommand request){ return speakerRepository.deleteByIdCustom(request.id);}
}
