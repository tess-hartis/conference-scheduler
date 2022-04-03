package com.example.conferencescheduler.domain;

import com.example.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.example.conferencescheduler.domain.valueobjects.NameTitle;
import com.example.conferencescheduler.infrastructure.DescriptionBioConverter;
import com.example.conferencescheduler.infrastructure.NameTitleConverter;
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
    @Convert(converter = NameTitleConverter.class)
    private NameTitle first_name;

    @Getter
    @Convert(converter = NameTitleConverter.class)
    private NameTitle last_name;

    @Getter
    @Convert(converter = NameTitleConverter.class)
    private NameTitle title;

    @Getter
    @Convert(converter = NameTitleConverter.class)
    private NameTitle company;

    @Getter
    @Convert(converter = DescriptionBioConverter.class)
    private DescriptionBio speaker_bio;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] speaker_photo;

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;

    public static Speaker of(NameTitle first_name, NameTitle last_name, NameTitle title, NameTitle company, DescriptionBio speaker_bio) {

        var speaker = new Speaker();
        speaker.first_name = first_name;
        speaker.last_name = last_name;
        speaker.title = title;
        speaker.company = company;
        speaker.speaker_bio = speaker_bio;
        return speaker;
    }

    public Speaker update(NameTitle first_name, NameTitle last_name, NameTitle title, NameTitle company, DescriptionBio speaker_bio){

        this.first_name = first_name;
        this.last_name = last_name;
        this.title = title;
        this.company = company;
        this.speaker_bio = speaker_bio;
        return this;
    }
    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

}