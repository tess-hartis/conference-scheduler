package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;

@Value
public class SpeakerFirstName {

    String name;

    public static SpeakerFirstName of(String name) {
        if (name == null)
            throw new IllegalArgumentException("Invalid name");

        if (name.isEmpty())
            throw new IllegalArgumentException("Invalid name");

        return new SpeakerFirstName(name);
    }

    public String value() { return this.name;}
}
