package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.conferencescheduler.domain.valueobjects.NameTitle;
import com.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.Value;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PostSpeakerCmdHandler implements Command.Handler<PostSpeakerCommand, Validation<List<String>, Speaker>>{

    private final SpeakerRepository speakerRepository;

    @Override
    public Validation<List<String>, Speaker> handle(PostSpeakerCommand command) {

        var firstName = NameTitle.validate(command.first_name);
        var lastName = NameTitle.validate(command.last_name);
        var title = NameTitle.validate(command.title);
        var company = NameTitle.validate(command.company);
        var bio = DescriptionBio.validate(command.speaker_bio);

        return Validation.combine(firstName, lastName, title, company, bio)
                .ap(Speaker::of)
                .map(speakerRepository::saveAndFlush)
                .mapError(Value::toJavaList);
    }
}
