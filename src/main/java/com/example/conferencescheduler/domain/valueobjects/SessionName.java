package com.example.conferencescheduler.domain.valueobjects;

import io.vavr.control.Validation;
import lombok.Value;

@Value
public class SessionName{

    public String value;

    private SessionName(String value){
        this.value = value;
    }

//   public static SessionName of(String name) {
//       if (name == null)
//           throw new IllegalArgumentException("Invalid name");
//
//       if (name.isEmpty())
//           throw new IllegalArgumentException("Invalid name");
//
//       return new SessionName(name);
//   }
//
//    public String value(){
//        return this.sessionName;
//    }

    public static Validation<String, SessionName> validate(String name){
        if (name.isEmpty())
            return Validation.invalid("Name cannot be empty");

        else return Validation.valid(new SessionName(name));
    }

}
