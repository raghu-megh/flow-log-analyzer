package com.illumeio.parser;

import com.illumeio.model.V2FlowLogLine;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class V2FlowLogParserTest {

    private final V2FlowLogParser parser = new V2FlowLogParser();

    @Test
    void testSanity() {
        String line = "2 123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK";
        var map = parser.parse(line);
        assertTrue(map.isPresent());
        var o = map.get();
        assertEquals(2, o.getVersion());
    }

    @Test
    void sanityTestFile() throws FileNotFoundException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        var url = classloader.getResource("V2-flow-log.log");
        assert url != null;
        List<V2FlowLogLine> lines = new V2FlowLogParser().parse(new FileReader(url.getFile()));
        assertEquals(14, lines.size());
    }

    @Test
    void testVersion2IsSupported() {
        String line = "2     123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK";
        var map = parser.parse(line);
        assertTrue(map.isPresent());
        var o = map.get();
        assertEquals("123456789012", o.getAccountId());
    }

    @Test
    void threeNonConformingLines() throws FileNotFoundException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        var url = classloader.getResource("V2-flow-log-3-faulty.log");
        assert url != null;
        List<V2FlowLogLine> lines = new V2FlowLogParser().parse(new FileReader(url.getFile()));
        assertEquals(13, lines.size());
    }
}