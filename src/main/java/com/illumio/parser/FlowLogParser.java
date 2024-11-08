package com.illumio.parser;

import com.illumio.model.DefaultFlowLogLine;

import java.io.Reader;
import java.util.List;

public interface FlowLogParser<X extends DefaultFlowLogLine> {

    int VERSION = 0;
    int ACCOUNT_ID = 1;
    int INTERFACE_ID = 2;
    int SOURCE_ADDRESS = 3;
    int DESTINATION_ADDRESS = 4;
    int SOURCE_PORT = 5;
    int DESTINATION_PORT = 6;
    int PROTOCOL = 7;
    int PACKETS_COUNT = 8;
    int BYTES_COUNT = 9;
    int START_TIME = 10;
    int END_TIME = 11;
    int ACTION = 12;
    int LOG_STATUS = 13;

    List<X> parse(Reader reader);
}
