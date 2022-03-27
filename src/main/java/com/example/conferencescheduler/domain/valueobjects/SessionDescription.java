package com.example.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;
import lombok.Value;

import java.util.Objects;

@Value
public class SessionDescription {

    public String value;

    public SessionDescription(String value){
        this.value = Objects.requireNonNullElse(value, "");
    }

    public String value() {
        if (this.sessionDescription == null)
            return "";

        return this.sessionDescription;
    }
}
