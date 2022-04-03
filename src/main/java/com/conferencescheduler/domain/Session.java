package com.conferencescheduler.domain;

import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.conferencescheduler.domain.valueobjects.NameTitle;
import com.conferencescheduler.domain.valueobjects.SessionLength;
import com.conferencescheduler.infrastructure.DescriptionBioConverter;
import com.conferencescheduler.infrastructure.NameTitleConverter;
import com.conferencescheduler.infrastructure.SessionLengthConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "sessions")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long session_id;

    @Getter
    @Convert(converter = NameTitleConverter.class)
    private NameTitle session_name;

    @Getter
    @Convert(converter = DescriptionBioConverter.class)
    private DescriptionBio session_description;

    @Getter
    @Convert(converter = SessionLengthConverter.class)
    private SessionLength session_length;

    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    public static Session of(NameTitle session_name, DescriptionBio session_description, SessionLength session_length) {

        var session = new Session();
        session.session_name = session_name;
        session.session_description = session_description;
        session.session_length = session_length;
        return session;
    }

    public Session update(NameTitle session_name, DescriptionBio session_description, SessionLength session_length) {
        this.session_name = session_name;
        this.session_description = session_description;
        this.session_length = session_length;
        return this;
    }

    public boolean addSpeaker(Speaker speaker){
        return speakers.add(speaker);
    }
}
