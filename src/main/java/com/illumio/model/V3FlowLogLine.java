package com.illumio.model;

public interface V3FlowLogLine extends DefaultFlowLogLine {

    String getVpcId();
    String getSubnetId();
    String getInstanceId();
    int getTcpFlags();
    String getType();
    String getPacketSourceAddress();
    String getPacketDestinationAddress();
}
