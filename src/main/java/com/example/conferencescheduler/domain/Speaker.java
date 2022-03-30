package com.example.conferencescheduler.domain;

import com.example.conferencescheduler.domain.valueobjects.SpeakerCompany;
import com.example.conferencescheduler.domain.valueobjects.SpeakerName;
import com.example.conferencescheduler.domain.valueobjects.SpeakerTitle;
import com.example.conferencescheduler.infrastructure.SpeakerCompanyConverter;
import com.example.conferencescheduler.infrastructure.SpeakerNameConverter;
import com.example.conferencescheduler.infrastructure.SpeakerTitleConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity(name = "speakers")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long speaker_id;

    @Getter
    @Convert(converter = SpeakerNameConverter.class)
    private SpeakerName first_name;

    @Getter
    @Convert(converter = SpeakerNameConverter.class)
    private SpeakerName last_name;

    @Getter
    @Convert(converter = SpeakerTitleConverter.class)
    private SpeakerTitle title;

    @Getter
    @Convert(converter = SpeakerCompanyConverter.class)
    private SpeakerCompany company;

    private String speaker_bio;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] speaker_photo;

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;

    public static Speaker of(SpeakerName first_name,
                             SpeakerName last_name,
                             SpeakerTitle title,
                             SpeakerCompany company,
                             String speaker_bio) {

        var speaker = new Speaker();
        speaker.first_name = first_name;
        speaker.last_name = last_name;
        speaker.title = title;
        speaker.company = company;
        speaker.speaker_bio = speaker_bio;
        return speaker;
    }

    public Speaker Update(SpeakerName first_name,
                          SpeakerName last_name,
                          SpeakerTitle title,
                          SpeakerCompany company,
                          String speaker_bio){

        this.first_name = first_name;
        this.last_name = last_name;
        this.title = title;
        this.company = company;
        this.speaker_bio = speaker_bio;
        return this;
    }

    public String getSpeaker_bio() { return speaker_bio; }
    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }
    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }
}