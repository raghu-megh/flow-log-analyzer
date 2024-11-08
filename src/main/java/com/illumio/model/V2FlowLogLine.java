package com.illumio.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class V2FlowLogLine implements DefaultFlowLogLine {

    private final int version;
    private final String accountId;
    private final String interfaceId;
    private final String sourceAddress;
    private final String destinationAddress;
    private final String sourcePort;
    private final String destinationPort;
    private final int protocolNumber;
    private final int packets;
    private final int bytes;
    private final int startTime;
    private final int endTime;
    private final String action;
    private final String logStatus;

    public int getVersion() {
        return 2;
    }
}
