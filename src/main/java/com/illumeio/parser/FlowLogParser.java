package com.illumeio.parser;

import com.illumeio.model.DefaultFlowLogLine;

import java.io.Reader;
import java.util.List;
import java.util.Optional;

public interface FlowLogParser {

    List<? extends DefaultFlowLogLine> parse(Reader reader);
}
