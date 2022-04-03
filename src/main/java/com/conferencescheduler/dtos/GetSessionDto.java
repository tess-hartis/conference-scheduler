package com.conferencescheduler.dtos;

import com.conferencescheduler.domain.Session;

public class GetSessionDto {

    private Long session_id;
    private String session_name;
    private String session_description;
    private Integer session_length;

    public static GetSessionDto fromSession(Session session) {

        var dto = new GetSessionDto();
        dto.session_id = session.getSession_id();
        dto.session_name = session.getSession_name().value;
        dto.session_length = session.getSession_length().value;

        if (session.getSession_description() == null)
            dto.session_description = "No description provided";

        if (session.getSession_description() != null)
            dto.session_description = session.getSession_description().value;

        return dto;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
