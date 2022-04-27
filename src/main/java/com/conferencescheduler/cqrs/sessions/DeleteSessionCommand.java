package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteSessionCommand implements Command<Integer> {

    Long id;

}
