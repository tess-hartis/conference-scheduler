package com.conferencescheduler.infrastructure;

import com.conferencescheduler.domain.valueobjects.SessionLength;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SessionLengthConverter implements AttributeConverter<SessionLength, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SessionLength sessionLength) {
        if (sessionLength == null) {
            return null;
        }
        return sessionLength.getValue();
    }

    @Override
    public SessionLength convertToEntityAttribute(Integer dbSessionLength) {
        if (dbSessionLength == null) {
            return null;
        }

        return SessionLength.unsafe(dbSessionLength);
    }
}
