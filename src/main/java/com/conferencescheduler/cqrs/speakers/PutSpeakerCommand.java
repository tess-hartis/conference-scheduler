package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PutSpeakerCommand implements Command<Option<Validation<List<String>, Speaker>>> {

    Long id;
    String first_name;
    String last_name;
    String title;
    String company;
    String speaker_bio;

    public void setId(Long id){
        this.id = id;
    }

}
