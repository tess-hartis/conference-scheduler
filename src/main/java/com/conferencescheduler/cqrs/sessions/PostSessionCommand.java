package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PostSessionCommand implements Command<Validation<List<String>, Session>> {

    String session_name;
    String session_description;
    Integer session_length;
}
