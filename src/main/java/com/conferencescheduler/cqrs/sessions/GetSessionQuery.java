package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetSessionQuery implements Command<Option<Session>> {

    Long id;
}
