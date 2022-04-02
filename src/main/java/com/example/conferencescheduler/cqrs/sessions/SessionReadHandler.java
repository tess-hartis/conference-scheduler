package com.example.conferencescheduler.cqrs.sessions;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.repositories.SessionRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SessionReadHandler {

    private final SessionRepository sessionRepository;

    public Option<Session> handleGetOne(GetSessionQuery request){
        return sessionRepository.findByIdOption(request.id);
    }

    public List<Session> handleGetAll(GetSessionsQuery request){
        return sessionRepository.findAll();
    }
}
