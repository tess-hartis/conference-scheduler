package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import com.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GetSpeakerQueryHandler implements Command.Handler<GetSpeakerQuery, Option<Speaker>>{

    private final SpeakerRepository speakerRepository;

    @Override
    public Option<Speaker> handle(GetSpeakerQuery query) {
        return speakerRepository.findByIdOption(query.id);
    }
}
