package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

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
