package com.example.conferencescheduler.cqrs;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.repositories.SessionRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetSessionsHandler {

    private final SessionRepository sessionRepository;

    public List<Session> handle(GetSessionsQuery query){
        return sessionRepository.findAll();
    }
}
