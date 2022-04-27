package com.conferencescheduler.controllers;

import an.awesome.pipelinr.Pipeline;
import com.conferencescheduler.cqrs.services.PostSessionSpeakerCmd;
import com.conferencescheduler.cqrs.sessions.*;
import com.conferencescheduler.dtos.GetSessionDto;
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
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private final Pipeline pipeline;

    @GetMapping
    public List<GetSessionDto> list(GetSessionsQuery query) {

        var response = query.execute(pipeline);
        return response.stream().map(GetSessionDto::fromSession).collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity get(@PathVariable Long id) {

        var response = new GetSessionQuery(id).execute(pipeline);
        return response.fold(() -> notFound().build(), session -> ok(GetSessionDto.fromSession(session)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PostSessionCommand command) {

        var response = command.execute(pipeline);
        return response.fold(e -> unprocessableEntity().body(e), s -> ok(GetSessionDto.fromSession(s)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

        var response = new DeleteSessionCommand(id).execute(pipeline);
        return Match(response).of(
                Case($(0), new ResponseEntity<>(HttpStatus.NOT_FOUND)),
                Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody PutSessionCommand command) {

        command.setId(id);
        var response = command.execute(pipeline);
        return response.fold(() -> notFound().build(),
                session -> session.fold(errors -> unprocessableEntity().body(errors),
                        updated -> ok(GetSessionDto.fromSession(updated))));
    }

    @PostMapping
    @RequestMapping("{sessionId}/speaker/{speakerId}")
    public ResponseEntity addSpeaker(@PathVariable Long sessionId, @PathVariable Long speakerId){

        var response = new PostSessionSpeakerCmd(sessionId, speakerId).execute(pipeline);
        return response.fold(() -> badRequest().build(), session -> ok("Successfully added"));
    }

}
