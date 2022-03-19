package com.example.conferencescheduler.domain.valueobjects;

import com.example.conferencescheduler.domain.Session;
import com.sun.istack.NotNull;
import lombok.Value;

@Value
public class SessionLength {

    private Integer sessionLength;

    public static SessionLength of(Integer length) {
        if (length < 15) {
            throw new IllegalArgumentException("Invalid length");
        }
        if (length > 120) {
            throw new IllegalArgumentException("Invalid length");
        }

        return new SessionLength(length);
    }

    public Integer value() {
        return this.sessionLength;
    }
}
