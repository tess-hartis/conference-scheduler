package com.example.conferencescheduler.controllers;

import com.example.conferencescheduler.cqrs.speakers.*;
import com.example.conferencescheduler.dtos.GetSpeakerDto;
import io.vavr.API;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.unprocessableEntity;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

   private final PostSpeakerHandler postSpeakerHandler;
   private final PutSpeakerHandler putSpeakerHandler;
   private final DeleteSpeakerHandler deleteSpeakerHandler;
   private final GetSpeakerHandler getSpeakerHandler;
   private final GetSpeakersHandler getSpeakersHandler;

    @GetMapping
    public List<GetSpeakerDto> list() {

        var request = new GetSpeakersQuery();
        var response = getSpeakersHandler.handle(request);
        return response.stream()
                .map(GetSpeakerDto::fromSpeaker)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<GetSpeakerDto> get(@PathVariable Long id){

        var request = new GetSpeakerQuery(id);
        var response = getSpeakerHandler.handle(request);
        return Match(response).of(
                Case($Some($()), x -> new ResponseEntity<>(GetSpeakerDto.fromSpeaker(x), HttpStatus.OK)),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PostSpeakerCommand request){

        var response = postSpeakerHandler.handle(request);
        return response.fold(e -> unprocessableEntity().body(e), s -> ok(GetSpeakerDto.fromSpeaker(s)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

       var request = new DeleteSpeakerCommand(id);
       var response = deleteSpeakerHandler.handle(request);
       return API.Match(response).of(
               Case($(0), new ResponseEntity<>(HttpStatus.BAD_REQUEST)),
               Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody PutSpeakerCommand request) {

        request.id = id;
        var response = putSpeakerHandler.handle(request);
        return Match(response).of(
                Case($Some($()), x ->
                        x.fold(e -> unprocessableEntity().body(e), s -> ok(GetSpeakerDto.fromSpeaker(s)))),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));

    }
}
