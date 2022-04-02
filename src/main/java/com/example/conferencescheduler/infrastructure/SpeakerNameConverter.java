package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SpeakerName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SpeakerNameConverter implements AttributeConverter<SpeakerName, String> {

    @Override
    public String convertToDatabaseColumn(SpeakerName speakerName) {
        if (speakerName == null) {
            return null;
        }
        return speakerName.value();
    }

    @Override
    public SpeakerName convertToEntityAttribute(String dbSpeakerName) {
        if (dbSpeakerName == null || dbSpeakerName.isEmpty()) {
            return null;
        }
        return new SpeakerName(dbSpeakerName);
    }
}
