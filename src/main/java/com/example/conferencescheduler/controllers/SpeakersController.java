package com.example.conferencescheduler.controllers;

import com.example.conferencescheduler.domain.Speaker;
import com.example.conferencescheduler.domain.valueobjects.SpeakerCompany;
import com.example.conferencescheduler.domain.valueobjects.SpeakerFirstName;
import com.example.conferencescheduler.domain.valueobjects.SpeakerLastName;
import com.example.conferencescheduler.domain.valueobjects.SpeakerTitle;
import com.example.conferencescheduler.dtos.GetSpeakerDto;
import com.example.conferencescheduler.dtos.PostSpeakerDto;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.API;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    private final SpeakerRepository speakerRepository;

    public SpeakersController(SpeakerRepository speakerRepository){
        this.speakerRepository = speakerRepository;
    }

    @GetMapping
    public List<GetSpeakerDto> list() {

        var speakers = speakerRepository.findAll();
        return speakers.stream()
                .map(GetSpeakerDto::fromSpeaker)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<GetSpeakerDto> get(@PathVariable Long id){

        var input = speakerRepository.findByIdOption(id);
        return API.Match(input).of(
                Case($Some($()), x -> new ResponseEntity<>(GetSpeakerDto.fromSpeaker(x), HttpStatus.OK)),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public void create(@RequestBody PostSpeakerDto dto){

        var firstName = SpeakerFirstName.of(dto.first_name);
        var lastName = SpeakerLastName.of(dto.last_name);
        var title = SpeakerTitle.of(dto.title);
        var company = SpeakerCompany.of(dto.company);
        var bio = dto.speaker_bio;

        var speaker = Speaker.of(firstName, lastName, title, company, bio);
        speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

       var input = speakerRepository.deleteByIdInteger(id);
       return API.Match(input).of(
               Case($(0), new ResponseEntity<>(HttpStatus.BAD_REQUEST)),
               Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody PostSpeakerDto dto) {

        Speaker existingSpeaker = speakerRepository.getById(id);

        var firstName = SpeakerFirstName.of(dto.first_name);
        var lastName = SpeakerLastName.of(dto.last_name);
        var title = SpeakerTitle.of(dto.title);
        var company = SpeakerCompany.of(dto.company);
        var bio = dto.speaker_bio;
        var speaker = existingSpeaker.Update(firstName, lastName, title, company, bio);

        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        speakerRepository.saveAndFlush(existingSpeaker);
    }
}
