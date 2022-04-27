package com.conferencescheduler.cqrs.services;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostSessionSpeakerCmd implements Command<Option<Session>> {

    Long sessionId;
    Long speakerId;
}
