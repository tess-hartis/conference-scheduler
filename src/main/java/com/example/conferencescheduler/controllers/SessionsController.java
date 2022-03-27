package com.example.conferencescheduler.controllers;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.domain.valueobjects.SessionDescription;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.SessionName;
import com.example.conferencescheduler.dtos.GetSessionDto;
import com.example.conferencescheduler.dtos.PostSessionDto;
import com.example.conferencescheduler.repositories.SessionRepository;
import io.vavr.control.Validation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Patterns.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    private final SessionRepository sessionRepository;

    public SessionsController(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping
    public List<GetSessionDto> list() {

        var sessions = sessionRepository.findAll();
        return sessions.stream()
                .map(GetSessionDto::fromSession)
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<GetSessionDto> get(@PathVariable Long id) {

        var input = sessionRepository.findByIdOption(id);
        return Match(input).of(
                Case($Some($()), x -> new ResponseEntity<>(GetSessionDto.fromSession(x), HttpStatus.OK)),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PostSessionDto dto) {

        var name = SessionName.validate(dto.session_name);
        var description = SessionDescription.validate(dto.session_description);
        var length = SessionLength.validate(dto.session_length);

        return Validation.combine(name, description, length)
                .ap(Session::of)
                .fold(e -> unprocessableEntity().body(e.toJavaList()),
                      c -> ok(sessionRepository.saveAndFlush(c)));

//                .map(sessionRepository::saveAndFlush)
//                .mapError(Value::toJavaList);

//        return Match(result).of(
//                Case($Valid($()),() -> new ResponseEntity<>(HttpStatus.CREATED)),
//                Case($Invalid($()), e -> new ResponseEntity<>(e, HttpStatus.BAD_REQUEST)));

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

        var input = sessionRepository.deleteByIdInteger(id);
        return Match(input).of(
                Case($(0), new ResponseEntity<>(HttpStatus.BAD_REQUEST)),
                Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostSessionDto dto) {

        var existingSession = sessionRepository.findByIdOption(id);

        var name = SessionName.validate(dto.session_name);
        var description = SessionDescription.validate(dto.session_description);
        var length = SessionLength.validate(dto.session_length);

        var result = existingSession
                .map(s -> Validation.combine(name, description, length)
                        .ap(s::Update));

        return Match(result).of(
                Case($Some($()),
                        x -> x.fold(e -> unprocessableEntity().body(e.toJavaList()),
                                s -> ok(sessionRepository.saveAndFlush(s)))),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));

//        BeanUtils.copyProperties(session, existingSession, "session_id");
//        sessionRepository.saveAndFlush(existingSession);
    }
}
