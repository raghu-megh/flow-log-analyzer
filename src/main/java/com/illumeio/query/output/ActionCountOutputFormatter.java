package com.illumeio.query.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ActionCountOutputFormatter implements OutputFormatter<Map<String, Integer>> {

    @Override
    public void write(Writer writer, Map<String, Integer> result) throws IOException {
        writer.write("Action Counts:\n");
        writer.write("Action,Count\n");
        result.forEach((key, value) -> {
            try {
                writer.write(key + "," + value + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        writer.flush();
    }
}
