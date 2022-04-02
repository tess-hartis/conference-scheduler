package com.example.conferencescheduler.cqrs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostSessionCommand {

    public String session_name;
    public String session_description;
    public Integer session_length;
}
