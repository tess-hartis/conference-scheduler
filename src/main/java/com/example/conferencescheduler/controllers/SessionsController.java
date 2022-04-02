package com.example.conferencescheduler.controllers;

import com.example.conferencescheduler.cqrs.*;
import com.example.conferencescheduler.dtos.GetSessionDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static org.springframework.http.ResponseEntity.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private final PostSessionHandler postSessionHandler;
    private final PutSessionHandler putSessionHandler;
    private final DeleteSessionHandler deleteSessionHandler;
    private final GetSessionHandler getSessionHandler;
    private final GetSessionsHandler getSessionsHandler;


    @GetMapping
    public List<GetSessionDto> list() {

        var request = new GetSessionsQuery();
        var response = getSessionsHandler.handle(request);
        return response.stream()
                .map(GetSessionDto::fromSession)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity get(@PathVariable Long id) {

        var request = new GetSessionQuery(id);
        var response = getSessionHandler.handle(request);
        return Match(response).of(
                Case($Some($()), x -> new ResponseEntity<>(GetSessionDto.fromSession(x), HttpStatus.OK)),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));

//        return response.fold(notFound(), x -> new ResponseEntity<>(GetSessionDto.fromSession(x), HttpStatus.OK));
//        return response.map(x -> ok(GetSessionDto.fromSession(x))).orElse(notFound());

    }

    @PostMapping
    public ResponseEntity create(@RequestBody PostSessionCommand request) {

        var response = postSessionHandler.handle(request);
        return response.fold(e -> unprocessableEntity().body(e), s -> ok(GetSessionDto.fromSession(s)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

        var request = new DeleteSessionCommand(id);
        var response = deleteSessionHandler.handle(request);
        return Match(response).of(
                Case($(0), new ResponseEntity<>(HttpStatus.NOT_FOUND)),
                Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody PutSessionCommand request) {

        request.id = id;
        var response = putSessionHandler.handle(request);
        return Match(response).of(
                Case($Some($()), x ->
                        x.fold(e -> unprocessableEntity().body(e), s -> ok(GetSessionDto.fromSession(s)))),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

}
