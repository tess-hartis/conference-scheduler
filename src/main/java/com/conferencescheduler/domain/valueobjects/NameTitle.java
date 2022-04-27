package com.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;
import lombok.NonNull;
import lombok.Value;

@Value
public class NameTitle {

    @NonNull String value;

    public static Validation<String, NameTitle> validate(String name){
        if (name.isEmpty())
            return Validation.invalid("Name cannot be empty");

        else return Validation.valid(new NameTitle(name));
    }

    public static NameTitle unsafe(String value){
        return new NameTitle(value);
    }

}
