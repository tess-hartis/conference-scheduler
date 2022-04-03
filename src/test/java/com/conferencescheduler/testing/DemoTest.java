package com.conferencescheduler.testing;

import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

class DemoTest {

    @Test
    public void javaOptional() {
        String s = null;
        Optional<String> opt = Optional.of(s);
        assertTrue(opt.isEmpty());
    }
    //throws NPE

    @Test
    public void vavrOption() {
        String s = null;
        Option<String> opt = Option.of(s);
        assertEquals("None", opt.toString());
    }
    //does not throw NPE
}