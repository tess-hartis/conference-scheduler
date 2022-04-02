package com.example.conferencescheduler.cqrs;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.domain.valueobjects.SessionDescription;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.SessionName;
import com.example.conferencescheduler.repositories.SessionRepository;
import io.vavr.Value;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PostSessionHandler {

    private final SessionRepository sessionRepository;

    public Validation<List<String>, Session> handle(PostSessionCommand command){
        var name = SessionName.validate(command.session_name);
        var description = SessionDescription.validate(command.session_description);
        var length = SessionLength.validate(command.session_length);

        return Validation.combine(name, description, length)
                .ap(Session::of)
                .map(sessionRepository::saveAndFlush)
                .mapError(Value::toJavaList);
    }
}
