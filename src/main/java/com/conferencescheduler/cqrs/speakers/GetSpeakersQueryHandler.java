package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import com.conferencescheduler.repositories.SpeakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetSpeakersQueryHandler implements Command.Handler<GetSpeakersQuery, List<Speaker>>{

    private final SpeakerRepository speakerRepository;

    @Override
    public List<Speaker> handle(GetSpeakersQuery query) {
        return speakerRepository.findAll();
    }
}
