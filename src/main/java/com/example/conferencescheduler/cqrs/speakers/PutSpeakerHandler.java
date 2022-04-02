package com.example.conferencescheduler.cqrs.speakers;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.example.conferencescheduler.domain.valueobjects.NameTitle;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.Value;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PutSpeakerHandler {

    private final SpeakerRepository speakerRepository;

    public Option<Validation<List<String>, Speaker>> handle(PutSpeakerCommand request){
        var existingSpeaker = speakerRepository.findByIdOption(request.id);

        var firstName= NameTitle.validate(request.first_name);
        var lastName = NameTitle.validate(request.last_name);
        var title = NameTitle.validate(request.title);
        var company = NameTitle.validate(request.company);
        var bio = DescriptionBio.validate(request.speaker_bio);

        return existingSpeaker
                .map(s -> Validation.combine(firstName, lastName, title, company, bio)
                        .ap(s::Update)
                        .map(speakerRepository::saveAndFlush)
                        .mapError(Value::toJavaList));
    }

}