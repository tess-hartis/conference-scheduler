package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SpeakerLastName;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SpeakerLastNameConverter implements AttributeConverter<SpeakerLastName, String> {

    @Override
    public String convertToDatabaseColumn(SpeakerLastName speakerLastName) {
        if (speakerLastName == null) {
            return null;
        }
        return speakerLastName.value();
    }

    @Override
    public SpeakerLastName convertToEntityAttribute(String dbSpeakerLastName) {
        if (dbSpeakerLastName == null || dbSpeakerLastName.isEmpty()) {
            return null;
        }
        return new SpeakerLastName(dbSpeakerLastName);
    }
}
