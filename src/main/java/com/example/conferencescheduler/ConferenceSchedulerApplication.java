package com.example.conferencescheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages=
        {
                "com.example.conferencescheduler.cqrs.sessions",
                "com.example.conferencescheduler.cqrs.speakers",
                "com.example.conferencescheduler.controllers",
                "com.example.conferencescheduler.domain",
                "com.example.conferencescheduler.dtos",
                "com.example.conferencescheduler.infrastructure",
                "com.example.conferencescheduler.repositories"
        })
public class ConferenceSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenceSchedulerApplication.class, args);
    }

}
