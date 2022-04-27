package com.conferencescheduler.cqrs.services;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Session;
import com.conferencescheduler.repositories.SessionRepository;
import com.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class SpeakerSessionHandler implements Command.Handler<PostSessionSpeakerCmd, Option<Session>> {

    private final SessionRepository sessionRepository;
    private final SpeakerRepository speakerRepository;

    @Override
    @Transactional
    public Option<Session> handle(PostSessionSpeakerCmd command) {

        var speaker = speakerRepository.findByIdOption(command.speakerId);
        var session = sessionRepository.findByIdOption(command.sessionId);

        return speaker.flatMap(speak -> session.map(sesh -> {
            sesh.addSpeaker(speak);
            return sessionRepository.save(sesh);
        }));
    }
}
