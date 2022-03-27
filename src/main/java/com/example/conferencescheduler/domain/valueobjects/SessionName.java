package com.example.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;
import lombok.Value;

@Value
public class SessionName{

    public String value;

    public SessionName(String value){
        this.value = value;
    }

       if (name.isEmpty())
           throw new IllegalArgumentException("Invalid name");

       return new SessionName(name);
   }

    public String value(){
        return this.sessionName;
    }
}
