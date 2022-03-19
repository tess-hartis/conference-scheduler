package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;

@Value
public class SpeakerTitle {

    String title;

    public static SpeakerTitle of(String title) {
        if (title == null)
            throw new IllegalArgumentException("Invalid title");

        if (title.isEmpty())
            throw new IllegalArgumentException("Invalid title");

        return new SpeakerTitle(title);
    }

    public String value() { return this.title;}
}
