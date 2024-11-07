package com.illumeio.query.output;

import java.io.IOException;
import java.io.Writer;

public interface OutputFormatter<R> {

    void write(Writer writer, R result) throws IOException;
}
