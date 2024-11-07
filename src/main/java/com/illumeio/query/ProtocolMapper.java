package com.illumeio.query;

import java.util.Optional;

public interface ProtocolMapper {

    Optional<String> getProtocol(int protocolNumber);
}
