package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteSessionCmdHandler implements Command.Handler<DeleteSessionCommand, Integer> {

    private final SessionRepository sessionRepository;

    @Override
    public Integer handle(DeleteSessionCommand command) {

        return sessionRepository.deleteByIdCustom(command.id);
    }
}
