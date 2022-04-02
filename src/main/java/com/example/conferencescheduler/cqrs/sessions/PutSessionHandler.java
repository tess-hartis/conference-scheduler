package com.example.conferencescheduler.cqrs.sessions;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.NameTitle;
import com.example.conferencescheduler.repositories.SessionRepository;
import io.vavr.Value;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PutSessionHandler {

    private final SessionRepository sessionRepository;

    public Option<Validation<List<String>, Session>> handle(PutSessionCommand request){

        var existingSession = sessionRepository.findByIdOption(request.id);

        var name= NameTitle.validate(request.session_name);
        var description = DescriptionBio.validate(request.session_description);
        var length = SessionLength.validate(request.session_length);

        return existingSession
                .map(s -> Validation.combine(name, description, length)
                        .ap(s::update)
                        .map(sessionRepository::saveAndFlush)
                        .mapError(Value::toJavaList));
    }
}
