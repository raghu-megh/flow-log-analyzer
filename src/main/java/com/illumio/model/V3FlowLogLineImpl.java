package com.illumio.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class V3FlowLogLineImpl extends V2FlowLogLine implements V3FlowLogLine {

    private final String vpcId;
    private final String subnetId;
    private final String instanceId;
    private final int tcpFlags;
    private final String type;
    private final String packetSourceAddress;
    private final String packetDestinationAddress;

    @Override
    public int getVersion() {
        return 3;
    }
}