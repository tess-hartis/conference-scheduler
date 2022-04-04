package com.conferencescheduler.testing;

import com.conferencescheduler.domain.Speaker;
import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.conferencescheduler.domain.valueobjects.NameTitle;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeakerTest {

    @Test
    void can_create_speaker_if_valid() {

        //Arrange

        var fn = NameTitle.validate("Tess");
        var ln = NameTitle.validate("Hartis");
        var t = NameTitle.validate("Developer");
        var c = NameTitle.validate("Somewhere");
        var b = DescriptionBio.validate("This is the bio");

        //Act

        var speaker = Validation.combine(fn, ln, t, c, b).ap(Speaker::of);

        //Assert

        speaker.fold(e -> fail(), s -> {
            assertSame("Tess", s.getFirst_name().value);
            assertSame("Hartis", s.getLast_name().value);
            assertSame("Developer", s.getTitle().value);
            assertSame("Somewhere", s.getCompany().value);
            assertSame("This is the bio", s.getSpeaker_bio().value);
            return null;
        });

        assertTrue(speaker.isValid());
    }

    @Test
    void does_not_create_if_invalid() {

        //Arrange

        var fn = NameTitle.validate("");
        var ln = NameTitle.validate("Hartis");
        var t = NameTitle.validate("Developer");
        var c = NameTitle.validate("Somewhere");
        var b = DescriptionBio.validate("");

        //Act

        var speaker = Validation.combine(fn, ln, t, c, b).ap(Speaker::of);

        //Assert

        speaker.fold(e -> {
            assertSame(e.head(), "Name cannot be empty");
            assertSame(e.tail().head(), "Description is too short");
            return null;
        }, s -> fail());
    }

    @Test
    void can_update_if_valid() {

        //Arrange

        var fn = NameTitle.validate("Tess");
        var ln = NameTitle.validate("Hartis");
        var t = NameTitle.validate("Developer");
        var c = NameTitle.validate("Somewhere");
        var b = DescriptionBio.validate("Very cool");
        var speaker = Validation.combine(fn, ln, t, c, b).ap(Speaker::of);

        var newFn = NameTitle.validate("Anthony");
        var newLn = NameTitle.validate("Adrian");
        var newT = NameTitle.validate("Engineer");
        var newC = NameTitle.validate("Here");
        var newB = DescriptionBio.validate("Extra cool");

        //Act

        var updated = speaker
                .flatMap(s -> Validation.combine(newFn, newLn, newT, newC, newB)
                        .ap(s::update));

        //Assert

        updated.fold(e -> fail(), s -> {
            assertSame("Anthony", s.getFirst_name().value);
            assertSame("Adrian", s.getLast_name().value);
            assertSame("Engineer", s.getTitle().value);
            assertSame("Here", s.getCompany().value);
            assertSame("Extra cool", s.getSpeaker_bio().value);
            return null;
        });

        assertTrue(updated.isValid());

    }

    @Test
    void does_not_update_if_invalid(){

        //Arrange

        var fn = NameTitle.validate("Tess");
        var ln = NameTitle.validate("Hartis");
        var t = NameTitle.validate("Developer");
        var c = NameTitle.validate("Somewhere");
        var b = DescriptionBio.validate("Very cool");
        var speaker = Validation.combine(fn, ln, t, c, b).ap(Speaker::of);

        var newFn = NameTitle.validate("");
        var newLn = NameTitle.validate("Adrian");
        var newT = NameTitle.validate("Engineer");
        var newC = NameTitle.validate("Here");
        var newB = DescriptionBio.validate("");

        //Act

        var updated = speaker
                .flatMap(s -> Validation.combine(newFn, newLn, newT, newC, newB)
                        .ap(s::update));

        //Assert

        updated.fold(e -> {
            assertSame(e.head(), "Name cannot be empty");
            assertSame(e.tail().head(), "Description is too short");
            return null;
        }, s -> fail());

        assertTrue(updated.isInvalid());

    }
}