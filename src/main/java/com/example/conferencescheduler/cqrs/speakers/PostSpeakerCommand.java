package com.example.conferencescheduler.cqrs.speakers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostSpeakerCommand {

    public String first_name;
    public String last_name;
    public String title;
    public String company;
    public String speaker_bio;
}
