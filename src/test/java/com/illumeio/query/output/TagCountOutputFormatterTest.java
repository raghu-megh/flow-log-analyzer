package com.illumeio.query.output;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.util.Map;

class TagCountOutputFormatterTest {

    @Test
    void write() {
        TagCountOutputFormatter utt = new TagCountOutputFormatter();
        utt.write(new PrintWriter(System.out, true), Map.of("sv_P2",1,
                "Untagged", 9,
                "email", 3));
    }
}