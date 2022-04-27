package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import com.conferencescheduler.repositories.SessionRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetSessionQueryHandler implements Command.Handler<GetSessionQuery, Option<Session>>{

    private final SessionRepository sessionRepository;

    @Override
    public Option<Session> handle(GetSessionQuery query) {
        return sessionRepository.findByIdOption(query.id);
    }
}
