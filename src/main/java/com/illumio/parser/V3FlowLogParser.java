package com.illumio.parser;

import com.illumio.model.V3FlowLogLine;
import com.illumio.model.V3FlowLogLineImpl;

import java.io.Reader;
import java.util.List;

public class V3FlowLogParser implements FlowLogParser<V3FlowLogLine> {

    @Override
    public List<V3FlowLogLine> parse(Reader reader) {
        return List.of(V3FlowLogLineImpl.builder()
                .instanceId("instanceId")
                .version(3)
                .build());
    }
}
