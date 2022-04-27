package com.conferencescheduler.dtos;

import com.conferencescheduler.domain.Speaker;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSpeakerDto {

    private Long speaker_id;
    private String first_name;
    private String last_name;
    private String title;
    private String company;
    private String speaker_bio;

    public static GetSpeakerDto fromSpeaker(Speaker speaker) {

        return new GetSpeakerDto(
                speaker.getSpeaker_id(),
                speaker.getFirst_name().value,
                speaker.getLast_name().value,
                speaker.getTitle().value,
                speaker.getCompany().value,
                speaker.getSpeaker_bio().value
        );
    }
}
