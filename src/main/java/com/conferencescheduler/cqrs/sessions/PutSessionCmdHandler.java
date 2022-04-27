package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.conferencescheduler.domain.valueobjects.NameTitle;
import com.conferencescheduler.domain.valueobjects.SessionLength;
import com.conferencescheduler.repositories.SessionRepository;
import io.vavr.Value;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PutSessionCmdHandler implements Command.Handler<PutSessionCommand, Option<Validation<List<String>, Session>>>{

    private final SessionRepository sessionRepository;

    @Override
    public Option<Validation<List<String>, Session>> handle(PutSessionCommand command) {

        var existingSession = sessionRepository.findByIdOption(command.id);

        var name= NameTitle.validate(command.session_name);
        var description = DescriptionBio.validate(command.session_description);
        var length = SessionLength.validate(command.session_length);

        return existingSession
                .map(s -> Validation.combine(name, description, length)
                        .ap(s::update)
                        .map(sessionRepository::saveAndFlush)
                        .mapError(Value::toJavaList));
    }
}
