package com.conferencescheduler.infrastructure;

import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DescriptionBioConverter implements AttributeConverter<DescriptionBio, String> {

    @Override
    public String convertToDatabaseColumn(DescriptionBio sessionDescription) {
        if (sessionDescription == null) {
            return null;
        }
        return sessionDescription.getValue();
    }

    @Override
    public DescriptionBio convertToEntityAttribute(String dbSessionDescription) {
        if (dbSessionDescription == null || dbSessionDescription.isEmpty()) {
            return null;
        }
        return DescriptionBio.unsafe(dbSessionDescription);
    }
}
