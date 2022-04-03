package com.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;

import java.util.Objects;

public class DescriptionBio {

    public String value;

    private DescriptionBio(String value){
        this.value = Objects.requireNonNullElse(value, "");
    }

    public static Validation<String, DescriptionBio> validate(String descriptionBio) {
        if (descriptionBio.length() > 2000)
            return Validation.invalid("Description or bio is too long");

        if(descriptionBio.isEmpty())
            return Validation.invalid("Description is too short");

        else return Validation.valid(new DescriptionBio(descriptionBio));
    }
}
