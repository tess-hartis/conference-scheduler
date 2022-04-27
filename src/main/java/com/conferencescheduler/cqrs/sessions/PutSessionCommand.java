package com.conferencescheduler.cqrs.sessions;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import io.vavr.control.Option;
import io.vavr.control.Validation;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PutSessionCommand implements Command<Option<Validation<List<String>, Session>>> {

    Long id;
    String session_name;
    String session_description;
    Integer session_length;

    public void setId(Long newId){
        this.id = newId;
    }
}
