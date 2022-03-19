package com.example.conferencescheduler.domain.valueobjects;

import lombok.Value;

@Value
public class SpeakerCompany {

    String company;

    public static SpeakerCompany of(String company){
        if (company == null)
            throw new IllegalArgumentException("Invalid company");

        if (company.isEmpty())
            throw new IllegalArgumentException("Invalid company");

        return new SpeakerCompany(company);
    }

    public String value() {return this.company;}
}
