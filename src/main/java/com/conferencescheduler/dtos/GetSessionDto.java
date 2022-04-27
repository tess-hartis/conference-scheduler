package com.conferencescheduler.dtos;

import com.conferencescheduler.domain.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSessionDto {

    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;

    public static GetSessionDto fromDomain(Session session) {

        return new GetSessionDto(
                session.getSession_id(),
                session.getSession_name().value,
                session.getSession_description().value,
                session.getSession_length().value
        );
    }
}
