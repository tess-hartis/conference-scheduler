package com.example.conferencescheduler.cqrs.sessions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PutSessionCommand {

    public Long id;
    public String session_name;
    public String session_description;
    public Integer session_length;

}
