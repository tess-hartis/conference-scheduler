package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SessionName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SessionNameConverter implements AttributeConverter<SessionName, String> {

    @Override
    public String convertToDatabaseColumn(SessionName sessionName) {
        if (sessionName == null) {
            return null;
        }
        return sessionName.value;
    }

    @Override
    public SessionName convertToEntityAttribute(String dbSessionName) {
        if (dbSessionName == null || dbSessionName.isEmpty()) {
            return null;
        }
        return new SessionName(dbSessionName);
    }
}
