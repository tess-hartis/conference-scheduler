package com.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;
import lombok.NonNull;
import lombok.Value;

@Value
public class DescriptionBio {

    @NonNull String value;

    public static Validation<String, DescriptionBio> validate(String descriptionBio) {
        if (descriptionBio.length() > 2000)
            return Validation.invalid("Description or bio is too long");

        if(descriptionBio.isEmpty())
            return Validation.invalid("Description is too short");

        else return Validation.valid(new DescriptionBio(descriptionBio));
    }

    public static DescriptionBio unsafe(String value){
        return new DescriptionBio(value);
    }
}
