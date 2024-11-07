package com.illumeio.query;

import java.util.Map;
import java.util.Optional;

/**
 * For simplicity purposes this is in a java class but this should be in a db and then
 * loaded into an in-memory cache for fast retrieval or query
 */
public final class StaticProtocolMapper implements ProtocolMapper {

    private static final Map<Integer, String> PROTOCOL_MAP =
            Map.of(0, "HOPOPT",
                    1, "ICMP",
                    2, "IGMP",
                    3, "GGP",
                    4, "IPv4",
                    5, "ST",
                    6, "TCP");

    @Override
    public Optional<String> getProtocol(int protocolNumber) {
        return Optional.ofNullable(PROTOCOL_MAP.get(protocolNumber))
                .map(String::toLowerCase);
    }
}
