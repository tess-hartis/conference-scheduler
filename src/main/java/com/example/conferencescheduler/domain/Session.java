package com.example.conferencescheduler.domain;

import com.example.conferencescheduler.domain.valueobjects.SessionDescription;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.SessionName;
import com.example.conferencescheduler.infrastructure.SessionDescriptionConverter;
import com.example.conferencescheduler.infrastructure.SessionLengthConverter;
import com.example.conferencescheduler.infrastructure.SessionNameConverter;
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
    @Convert(converter = SessionNameConverter.class)
    private SessionName session_name;

    @Getter
    @Convert(converter = SessionDescriptionConverter.class)
    private SessionDescription session_description;

    @Getter
    @Convert(converter = SessionLengthConverter.class)
    private SessionLength session_length;

    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    public static Session of(SessionName session_name, SessionDescription session_description, SessionLength session_length) {

        var session = new Session();
        session.session_name = session_name;
        session.session_description = session_description;
        session.session_length = session_length;
        return session;
    }

    public Session update(SessionName session_name, SessionDescription session_description, SessionLength session_length) {
        this.session_name = session_name;
        this.session_description = session_description;
        this.session_length = session_length;
        return this;
    }
}
