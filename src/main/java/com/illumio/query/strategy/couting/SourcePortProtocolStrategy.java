package com.illumio.query.strategy.couting;

import com.illumio.model.V2FlowLogLine;
import com.illumio.query.ProtocolMapper;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class SourcePortProtocolStrategy implements CountingStrategy<V2FlowLogLine> {

    private final ProtocolMapper protocolMapper;
    private final Map<String, Integer> map = new HashMap<>();

    @Override
    public void count(List<V2FlowLogLine> lines) {
        lines.forEach(this::count);
    }

    @Override
    public void count(V2FlowLogLine line) {
        protocolMapper.getProtocol(line.getProtocolNumber())
                .map(protocol -> line.getSourcePort() + "," + protocol)
                .ifPresent(srcPortProtocol -> {
                    map.put(srcPortProtocol, map.getOrDefault(srcPortProtocol, 0) + 1);
                });
    }

    @Override
    public Map<String, Integer> getCount() {
        return map;
    }
}
