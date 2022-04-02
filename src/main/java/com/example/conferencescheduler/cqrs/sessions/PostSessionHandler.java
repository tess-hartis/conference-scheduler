package com.example.conferencescheduler.cqrs.sessions;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.NameTitle;
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

    public Validation<List<String>, Session> handle(PostSessionCommand request){
        var name = NameTitle.validate(request.session_name);
        var description = DescriptionBio.validate(request.session_description);
        var length = SessionLength.validate(request.session_length);

        return Validation.combine(name, description, length)
                .ap(Session::of)
                .map(sessionRepository::saveAndFlush)
                .mapError(Value::toJavaList);
    }
}
