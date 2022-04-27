package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.repositories.SpeakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DeleteSpeakerCmdHandler implements Command.Handler<DeleteSpeakerCommand, Integer>{

    private final SpeakerRepository speakerRepository;

    @Override
    public Integer handle(DeleteSpeakerCommand command) {

        return speakerRepository.deleteByIdCustom(command.id);
    }
}
