package com.illumio.model;

public interface DefaultFlowLogLine {

    int getVersion();
    String getAccountId();
    String getInterfaceId();
    String getSourceAddress();
    String getDestinationAddress();
    String getSourcePort();
    String getDestinationPort();
    int getProtocolNumber();
    int getPackets();
    int getBytes();
    int getStartTime();
    int getEndTime();
    String getAction();
    String getLogStatus();
}
