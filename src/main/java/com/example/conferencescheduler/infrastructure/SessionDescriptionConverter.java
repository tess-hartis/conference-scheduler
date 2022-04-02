package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SessionDescription;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SessionDescriptionConverter implements AttributeConverter<SessionDescription, String> {

    @Override
    public String convertToDatabaseColumn(SessionDescription sessionDescription) {
        if (sessionDescription == null) {
            return null;
        }
        return sessionDescription.value;
    }

    @Override
    public SessionDescription convertToEntityAttribute(String dbSessionDescription) {
        if (dbSessionDescription == null || dbSessionDescription.isEmpty()) {
            return null;
        }
//        SessionDescription sessionDescription = new SessionDescription(dbSessionDescription);
//        return sessionDescription;
        return SessionDescription.validate(dbSessionDescription).get();
    }
}
