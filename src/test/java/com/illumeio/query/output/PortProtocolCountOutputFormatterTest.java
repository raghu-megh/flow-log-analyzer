package com.illumeio.query.output;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PortProtocolCountOutputFormatterTest {

    @Spy
    Writer writer;

    @Test
    void write() throws IOException {
        PortProtocolCountOutputFormatter utt = new PortProtocolCountOutputFormatter();
        utt.write(writer, Map.of("443,tcp",1,
                "443,udp", 9,
                "5000,smtp", 3));

        verify(writer, atLeastOnce()).write(anyString());
    }
}