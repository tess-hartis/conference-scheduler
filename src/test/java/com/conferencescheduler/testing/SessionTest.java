package com.conferencescheduler.testing;

import com.conferencescheduler.domain.Session;
import com.conferencescheduler.domain.Speaker;
import com.conferencescheduler.domain.valueobjects.DescriptionBio;
import com.conferencescheduler.domain.valueobjects.NameTitle;
import com.conferencescheduler.domain.valueobjects.SessionLength;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SessionTest {

    @Test
    void Can_Create_Session_When_Valid() {

        //Arrange

        var name = NameTitle.validate("Learn About Vavr");
        var description = DescriptionBio.validate("We will learn about Vavr");
        var length = SessionLength.validate(60);

        //Act

        var session = Validation.combine(name, description, length).ap(Session::of);

        //Assert

        session.fold(e -> fail(), s -> {
            assertSame("Learn About Vavr", s.getSession_name().value);
            assertSame("We will learn about Vavr", s.getSession_description().value);
            assertSame(60, s.getSession_length().value);
            return null;
        });

        assertTrue(session.isValid());
    }

    @Test
    void Does_Not_Create_Session_When_Invalid(){

        //Arrange

        var name = NameTitle.validate("");
        var description = DescriptionBio.validate("");
        var length = SessionLength.validate(1);

        //Act

        var session = Validation.combine(name, description, length).ap(Session::of);

        //Assert

        session.fold(e -> {
            assertSame(e.head(), "Name cannot be empty");
            assertSame(e.tail().head(), "Description is too short");
            assertSame(e.tail().tail().head(), "Invalid session length");
            return null;
        }, s -> fail());

        assertTrue(session.isInvalid());
    }

    @Test
    void Can_Update_Session_When_Valid(){

        //Arrange

        var name = NameTitle.validate("Learn About Vavr");
        var description = DescriptionBio.validate("We will learn about Vavr");
        var length = SessionLength.validate(60);
        var session = Validation.combine(name, description, length).ap(Session::of);

        var newName = NameTitle.validate("Learn About Java");
        var newDescription = DescriptionBio.validate("We will learn about Java");
        var newLength = SessionLength.validate(90);

        //Act

        var updated = session
                .flatMap(s -> Validation.combine(newName, newDescription, newLength)
                        .ap(s::update));

        //Assert

        updated.fold(e -> fail(), s -> {
            assertSame(s.getSession_name().value, "Learn About Java");
            assertSame(s.getSession_description().value, "We will learn about Java");
            assertSame(s.getSession_length().value, 90);
            return null;
        });

        assertTrue(updated.isValid());

    }

    @Test
    void Does_Not_Update_When_Invalid(){

        //Arrange

        var name = NameTitle.validate("Learn About Vavr");
        var description = DescriptionBio.validate("We will learn about Vavr");
        var length = SessionLength.validate(60);
        var session = Validation.combine(name, description, length).ap(Session::of);

        var newName = NameTitle.validate("");
        var newDescription = DescriptionBio.validate("");
        var newLength = SessionLength.validate(1);

        //Act

        var updated = session
                .flatMap(s -> Validation.combine(newName, newDescription, newLength)
                        .ap(s::update));

        //Assert

        updated.fold(e -> {
            assertSame(e.head(), "Name cannot be empty");
            assertSame(e.tail().head(), "Description is too short");
            assertSame(e.tail().tail().head(), "Invalid session length");
            return null;
        }, s -> fail());

        assertTrue(updated.isInvalid());
    }

    @Test
    void Can_Add_Speaker() {

        //ArrangeSession

        var name = NameTitle.validate("Learn About Vavr");
        var description = DescriptionBio.validate("We will learn about Vavr");
        var length = SessionLength.validate(60);
        var session = Validation.combine(name, description, length).ap(Session::of);

        //ArrangeSpeaker

        var fn = NameTitle.validate("Tess");
        var ln = NameTitle.validate("Hartis");
        var t = NameTitle.validate("Developer");
        var c = NameTitle.validate("Somewhere");
        var b = DescriptionBio.validate("This is the bio");
        var speaker = Validation.combine(fn, ln, t, c, b).ap(Speaker::of);

        //Act

        session.flatMap(sesh -> speaker.map(sesh::addSpeaker));

        //Assert




    }
}