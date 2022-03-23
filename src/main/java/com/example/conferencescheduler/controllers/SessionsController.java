package com.example.conferencescheduler.controllers;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.domain.valueobjects.SessionDescription;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.SessionName;
import com.example.conferencescheduler.dtos.GetSessionDto;
import com.example.conferencescheduler.dtos.PostSessionDto;
import com.example.conferencescheduler.repositories.SessionRepository;
import io.vavr.API;
import io.vavr.control.Option;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;

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
    public ResponseEntity<GetSessionDto> get(@PathVariable Long id){

        var input = sessionRepository.findByIdOption(id);
        return Match(input).of(
                Case($Some($()), x -> new ResponseEntity<>(GetSessionDto.fromSession(x), HttpStatus.OK)),
                Case($None(), () -> new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public void create(@RequestBody PostSessionDto dto){

        var name = SessionName.of(dto.session_name);
        var description = SessionDescription.of(dto.session_description);
        var length = SessionLength.of(dto.session_length);

        var session = Session.of(name, description, length);
        sessionRepository.saveAndFlush(session);

    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        //Also need to check for child records before deleting
        var input = sessionRepository.deleteByIdOption(id);
        return Match(input).of(
                Case($(0),new ResponseEntity<>(HttpStatus.BAD_REQUEST)),
                Case($(1), new ResponseEntity<>(HttpStatus.NO_CONTENT)));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void update(@PathVariable Long id, @RequestBody PostSessionDto dto) {

        Session existingSession = sessionRepository.getById(id);

        var name = SessionName.of(dto.session_name);
        var description = SessionDescription.of(dto.session_description);
        var length = SessionLength.of(dto.session_length);
        var session = existingSession.Update(name, description, length);

        BeanUtils.copyProperties(session, existingSession, "session_id");
        sessionRepository.saveAndFlush(existingSession);
    }
}
