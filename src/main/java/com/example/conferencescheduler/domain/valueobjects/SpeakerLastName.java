package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;

@Value
public class SpeakerLastName {

    String name;

    public static SpeakerLastName of(String name) {
        if (name == null)
            throw new IllegalArgumentException("Invalid name");

        if (name.isEmpty())
            throw new IllegalArgumentException("Invalid name");

        return new SpeakerLastName(name);
    }

    public String value() { return this.name;}
}
