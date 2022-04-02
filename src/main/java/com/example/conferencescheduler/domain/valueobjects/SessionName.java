package com.example.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;

public class SessionName{

    public String value;

    private SessionName(String value){
        this.value = value;
    }

    public static Validation<String, SessionName> validate(String name){
        if (name.isEmpty())
            return Validation.invalid("Name cannot be empty");

        else return Validation.valid(new SessionName(name));
    }

}
