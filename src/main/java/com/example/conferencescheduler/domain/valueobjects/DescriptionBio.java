package com.example.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;

import java.util.Objects;

public class DescriptionBio {

    public String value;

    private DescriptionBio(String value){
        this.value = Objects.requireNonNullElse(value, "");
    }

    public static Validation<String, DescriptionBio> validate(String descriptionBio) {
        if (descriptionBio.length() > 1000)
            return Validation.invalid("Description or bio is too long");

        else return Validation.valid(new DescriptionBio(descriptionBio));
    }
}
