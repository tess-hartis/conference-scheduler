package com.example.conferencescheduler.infrastructure;

import com.example.conferencescheduler.domain.valueobjects.NameTitle;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NameTitleConverter implements AttributeConverter<NameTitle, String> {

    @Override
    public String convertToDatabaseColumn(NameTitle nameTitle) {
        if (nameTitle == null) {
            return null;
        }
        return nameTitle.value;
    }

    @Override
    public NameTitle convertToEntityAttribute(String dbNameTitle) {
        if (dbNameTitle == null || dbNameTitle.isEmpty()) {
            return null;
        }

        return NameTitle.validate(dbNameTitle).get();
    }
}
