package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import javax.persistence.AttributeConverter;

public class SessionLengthConverter implements AttributeConverter<SessionLength, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SessionLength sessionLength) {
        if (sessionLength == null) {
            return null;
        }
        return sessionLength.value;
    }

    @Override
    public SessionLength convertToEntityAttribute(Integer dbSessionLength) {
        if (dbSessionLength == null) {
            return null;
        }

        return SessionLength.validate(dbSessionLength).get();
    }
}
