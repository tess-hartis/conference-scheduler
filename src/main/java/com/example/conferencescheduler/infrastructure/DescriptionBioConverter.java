package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.DescriptionBio;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DescriptionBioConverter implements AttributeConverter<DescriptionBio, String> {

    @Override
    public String convertToDatabaseColumn(DescriptionBio sessionDescription) {
        if (sessionDescription == null) {
            return null;
        }
        return sessionDescription.value;
    }

    @Override
    public DescriptionBio convertToEntityAttribute(String dbSessionDescription) {
        if (dbSessionDescription == null || dbSessionDescription.isEmpty()) {
            return null;
        }
        return DescriptionBio.validate(dbSessionDescription).get();
    }
}
