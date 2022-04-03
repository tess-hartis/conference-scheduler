package com.example.conferencescheduler.cqrs.services;

import com.example.conferencescheduler.domain.Session;
import com.example.conferencescheduler.repositories.SessionRepository;
import com.example.conferencescheduler.repositories.SpeakerRepository;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@AllArgsConstructor
public class SpeakerSessionHandler {

    private final SessionRepository sessionRepository;
    private final SpeakerRepository speakerRepository;

    @Transactional
    public Option<Session> addSessionSpeaker(Long sessionId, Long speakerId){
        var speaker = speakerRepository.findByIdOption(speakerId);
        var session = sessionRepository.findByIdOption(sessionId);

        return speaker.flatMap(speak -> session.map(sesh -> {
            sesh.addSpeaker(speak);
            return sessionRepository.save(sesh);
        }));

//        return speaker.flatMap(s -> session.map(s::addSession)
//                .map(x -> speakerRepository.saveAndFlush(s)));

//        return speaker.flatMap(speak -> session.map(sesh -> {
//            speak.addSession(sesh);
//            return speakerRepository.saveAndFlush(speak);
//        }));


//        if (!result.isEmpty())
//            result.map(x -> speakerRepository.saveAndFlush(speaker.get()));

//        return result;


//      var result = speaker.map(s -> session.map(x -> s.addSession(x)));
    }
}
