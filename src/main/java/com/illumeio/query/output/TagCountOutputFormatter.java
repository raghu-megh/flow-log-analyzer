package com.illumeio.query.output;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class TagCountOutputFormatter implements OutputFormatter<Map<String, Integer>> {

    @SneakyThrows
    @Override
    public void write(Writer writer, Map<String, Integer> result) {
        writer.write("Tag Counts\n");
        writer.write("Tag,Count\n");
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
