package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSpeakerCommand implements Command<Integer> {

    Long id;
}
