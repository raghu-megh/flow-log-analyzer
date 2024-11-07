package com.illumio.query;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StaticProtocolMapperTest {

    @Test
    void testSanity() {
        assertEquals(Optional.of("tcp"), new StaticProtocolMapper().getProtocol(6));
    }

}