package com.conferencescheduler.controllers;

import an.awesome.pipelinr.Pipeline;
import com.conferencescheduler.cqrs.speakers.*;
import com.conferencescheduler.dtos.GetSpeakerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

   private final Pipeline pipeline;

    @GetMapping
    public List<GetSpeakerDto> list(GetSpeakersQuery query) {

        var response = query.execute(pipeline);
        return response.stream().map(GetSpeakerDto::fromSpeaker).collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<GetSpeakerDto> get(@PathVariable Long id){

        var response = new GetSpeakerQuery(id).execute(pipeline);
        return response.fold(() -> notFound().build(), speaker -> ok(GetSpeakerDto.fromSpeaker(speaker)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PostSpeakerCommand command){

        var response = command.execute(pipeline);
        return response.fold(e -> unprocessableEntity().body(e), speaker -> ok(GetSpeakerDto.fromSpeaker(speaker)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

       var response = new DeleteSpeakerCommand(id).execute(pipeline);
       return Match(response).of(
               Case($(0), new ResponseEntity<>(HttpStatus.BAD_REQUEST)),
               Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody PutSpeakerCommand command) {

        command.setId(id);
        var response = command.execute(pipeline);
        return response.fold(() -> notFound().build(),
                speaker -> speaker.fold(errors -> unprocessableEntity().body(errors),
                        updated -> ok(GetSpeakerDto.fromSpeaker(updated))));
    }
}
