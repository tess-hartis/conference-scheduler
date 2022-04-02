package com.example.conferencescheduler.cqrs.sessions;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.repositories.SessionRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetSessionHandler {

    private final SessionRepository sessionRepository;

    public Option<Session> handle(GetSessionQuery request){
        return sessionRepository.findByIdOption(request.id);
    }
}
