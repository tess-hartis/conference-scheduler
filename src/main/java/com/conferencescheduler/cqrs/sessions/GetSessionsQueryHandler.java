package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import com.conferencescheduler.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetSessionsQueryHandler implements Command.Handler<GetSessionsQuery, List<Session>>{

    private final SessionRepository sessionRepository;

    @Override
    public List<Session> handle(GetSessionsQuery query) {
        return sessionRepository.findAll();
    }
}
