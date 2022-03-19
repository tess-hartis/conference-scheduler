package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.SpeakerCompany;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SpeakerCompanyConverter implements AttributeConverter<SpeakerCompany, String> {

    @Override
    public String convertToDatabaseColumn(SpeakerCompany speakerCompany) {
        if (speakerCompany == null) {
            return null;
        }
        return speakerCompany.value();
    }

    @Override
    public SpeakerCompany convertToEntityAttribute(String dbSpeakerCompany) {
        if (dbSpeakerCompany == null || dbSpeakerCompany.isEmpty()) {
            return null;
        }
        return new SpeakerCompany(dbSpeakerCompany);
    }
}
