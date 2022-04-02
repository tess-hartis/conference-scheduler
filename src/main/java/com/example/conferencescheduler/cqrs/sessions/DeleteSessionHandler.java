package com.example.conferencescheduler.cqrs.sessions;

import com.example.conferencescheduler.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteSessionHandler {

    private final SessionRepository sessionRepository;

    public Integer handle(DeleteSessionCommand request){
        return sessionRepository.deleteByIdCustom(request.id);
    }

}
