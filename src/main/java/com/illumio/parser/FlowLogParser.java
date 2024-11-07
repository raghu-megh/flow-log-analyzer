package com.illumio.parser;

import com.illumio.model.DefaultFlowLogLine;

import java.io.Reader;
import java.util.List;

public interface FlowLogParser {

    List<? extends DefaultFlowLogLine> parse(Reader reader);
}
