package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SpeakerTitle;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SpeakerTitleConverter implements AttributeConverter<SpeakerTitle, String> {

    @Override
    public String convertToDatabaseColumn(SpeakerTitle speakerTitle) {
        if (speakerTitle == null) {
            return null;
        }
        return speakerTitle.value();
    }

    @Override
    public SpeakerTitle convertToEntityAttribute(String dbSpeakerTitle) {
        if (dbSpeakerTitle == null || dbSpeakerTitle.isEmpty()) {
            return null;
        }
        return new SpeakerTitle(dbSpeakerTitle);
    }
}
