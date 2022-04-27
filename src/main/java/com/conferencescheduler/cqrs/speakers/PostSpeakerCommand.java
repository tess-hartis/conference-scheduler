package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PostSpeakerCommand implements Command<Validation<List<String>, Speaker>> {

    String first_name;
    String last_name;
    String title;
    String company;
    String speaker_bio;
}
