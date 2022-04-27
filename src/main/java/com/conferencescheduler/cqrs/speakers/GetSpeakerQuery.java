package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetSpeakerQuery implements Command<Option<Speaker>> {

    Long id;
}
