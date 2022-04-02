package com.example.conferencescheduler.cqrs.sessions;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GetSessionsHandler {

    private final SessionRepository sessionRepository;

    public List<Session> handle(GetSessionsQuery query){
        return sessionRepository.findAll();
    }
}
