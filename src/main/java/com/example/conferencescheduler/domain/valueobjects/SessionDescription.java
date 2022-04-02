package com.example.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;
import lombok.Value;

import java.util.Objects;

@Value
public class SessionDescription {

    public String value;

    private SessionDescription(String value){
        this.value = Objects.requireNonNullElse(value, "");
    }

    public static Validation<String, SessionDescription> validate(String description) {
        if (description.length() > 1000)
            return Validation.invalid("Description too long");

        else return Validation.valid(new SessionDescription(description));
    }
}
