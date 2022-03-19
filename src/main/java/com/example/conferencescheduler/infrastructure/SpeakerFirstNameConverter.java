package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.domain.valueobjects.SessionName;
import com.example.conferencescheduler.domain.valueobjects.SpeakerFirstName;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SpeakerFirstNameConverter implements AttributeConverter<SpeakerFirstName, String> {

    @Override
    public String convertToDatabaseColumn(SpeakerFirstName speakerFirstName) {
        if (speakerFirstName == null) {
            return null;
        }
        return speakerFirstName.value();
    }

    @Override
    public SpeakerFirstName convertToEntityAttribute(String dbSpeakerFirstName) {
        if (dbSpeakerFirstName == null || dbSpeakerFirstName.isEmpty()) {
            return null;
        }
        SpeakerFirstName speakerFirstName = new SpeakerFirstName(dbSpeakerFirstName);
        return speakerFirstName;
    }
}
