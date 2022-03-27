package com.example.conferencescheduler.domain.valueobjects;

import com.example.conferencescheduler.domain.Session;
import com.sun.istack.NotNull;
import lombok.Value;

@Value
public class SessionLength {

    public Integer value;

    public SessionLength(Integer value){

        this.value = value;
    }

//    public static SessionLength of(Integer length) {
//        if (length < 15) {
//            throw new IllegalArgumentException("Invalid length");
//        }
//        if (length > 120) {
//            throw new IllegalArgumentException("Invalid length");
//        }
//
//        return new SessionLength(length);
//    }
//
//    public Integer value() {
//        return this.value;
//    }

    public static Validation<String, SessionLength> validate(Integer length){
        if (length > 120 || length < 30)
            return Validation.invalid("Invalid session length");

        else return Validation.valid(new SessionLength(length));

    }
}
