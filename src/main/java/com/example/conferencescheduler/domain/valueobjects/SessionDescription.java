package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;
import org.hibernate.boot.model.naming.IllegalIdentifierException;

@Value
public class SessionDescription {

    private String sessionDescription;

    public static SessionDescription of(String description) {
        if (description == null)
            throw new IllegalArgumentException("Invalid description");

        if (description.isEmpty())
            throw new IllegalArgumentException("Invalid description");

        return new SessionDescription(description);
    }

    public String value() {
        if (this.sessionDescription == null)
            return "";

        return this.sessionDescription;
    }
}
