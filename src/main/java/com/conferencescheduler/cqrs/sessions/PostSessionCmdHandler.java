package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.conferencescheduler.domain.valueobjects.NameTitle;
import com.conferencescheduler.domain.valueobjects.SessionLength;
import com.conferencescheduler.repositories.SessionRepository;
import io.vavr.Value;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PostSessionCmdHandler implements Command.Handler<PostSessionCommand, Validation<List<String>, Session>>{

    private final SessionRepository sessionRepository;

    @Override
    public Validation<List<String>, Session> handle(PostSessionCommand command) {

        var name = NameTitle.validate(command.session_name);
        var description = DescriptionBio.validate(command.session_description);
        var length = SessionLength.validate(command.session_length);

        return Validation.combine(name, description, length)
                .ap(Session::of)
                .map(sessionRepository::saveAndFlush)
                .mapError(Value::toJavaList);
    }
}
