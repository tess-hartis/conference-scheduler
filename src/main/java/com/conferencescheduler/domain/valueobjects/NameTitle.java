package com.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;

public class NameTitle {

    //make private and add getter
    public String value;

    private NameTitle(String value){
        this.value = value;
    }

    public static Validation<String, NameTitle> validate(String name){
        if (name.isEmpty())
            return Validation.invalid("Name cannot be empty");

        else return Validation.valid(new NameTitle(name));
    }

}
