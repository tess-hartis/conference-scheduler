package com.example.conferencescheduler.cqrs.speakers;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.example.conferencescheduler.domain.valueobjects.NameTitle;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.Value;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PostSpeakerHandler {

    private final SpeakerRepository speakerRepository;

    public Validation<List<String>, Speaker> handle(PostSpeakerCommand request){
        var firstName = NameTitle.validate(request.first_name);
        var lastName = NameTitle.validate(request.last_name);
        var title = NameTitle.validate(request.title);
        var company = NameTitle.validate(request.company);
        var bio = DescriptionBio.validate(request.speaker_bio);

        return Validation.combine(firstName, lastName, title, company, bio)
                .ap(Speaker::of)
                .map(speakerRepository::saveAndFlush)
                .mapError(Value::toJavaList);
    }
}
