package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;

@Value
public class SpeakerName {

    String name;

    public static SpeakerName of(String name) {
        if (name == null)
            throw new IllegalArgumentException("Invalid name");

        if (name.isEmpty())
            throw new IllegalArgumentException("Invalid name");

        return new SpeakerName(name);
    }

    public String value() { return this.name;}
}
