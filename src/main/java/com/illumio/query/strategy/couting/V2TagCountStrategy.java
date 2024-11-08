package com.illumio.query.strategy.couting;

import com.illumio.model.V2FlowLogLine;
import com.illumio.query.ProtocolMapper;
import com.illumio.query.TagProvider;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class V2TagCountStrategy implements CountingStrategy<V2FlowLogLine> {

    private final TagProvider tagProvider;
    private final ProtocolMapper protocolMapper;
    private final Map<String, Integer> map = new HashMap<>();

    @Override
    public void count(List<V2FlowLogLine> lines) {
        lines.forEach(this::count);
    }

    @Override
    public void count(V2FlowLogLine line) {
        protocolMapper.getProtocol(line.getProtocolNumber())
                .map(protocol -> line.getDestinationPort() + "_" + protocol)
                .flatMap(s -> tagProvider.getTag(() -> s))
                .map(String::toLowerCase)
                .ifPresent(tag -> {
                    map.put(tag, map.getOrDefault(tag, 0) + 1);
                });
    }

    @Override
    public Map<String, Integer> getCount() {
        return map;
    }
}
