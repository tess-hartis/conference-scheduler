package com.example.conferencescheduler.dtos;

import lombok.Data;

@Data
public class PostSessionDto {

    public String session_name;
    public String session_description;
    public Integer session_length;

}
