package com.example.conferencescheduler.controllers;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.domain.valueobjects.SessionDescription;
import com.example.conferencescheduler.domain.valueobjects.SessionLength;
import com.example.conferencescheduler.domain.valueobjects.SessionName;
import com.example.conferencescheduler.dtos.GetSessionDto;
import com.example.conferencescheduler.dtos.PostSessionDto;
import com.example.conferencescheduler.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

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
    public GetSessionDto get(@PathVariable Long id){

        var session = sessionRepository.getById(id);
        return GetSessionDto.fromSession(session);
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
    public void delete(@PathVariable Long id) {
        //Also need to check for child records before deleting
        sessionRepository.deleteById(id);
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
