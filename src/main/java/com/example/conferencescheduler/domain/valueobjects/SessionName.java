package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;

@Value
public class SessionName{

    String sessionName;

   public static SessionName of(String name) {
       if (name == null)
           throw new IllegalArgumentException("Invalid name");

       if (name.isEmpty())
           throw new IllegalArgumentException("Invalid name");

       return new SessionName(name);
   }

    public String value(){
        return this.sessionName;
    }
}
