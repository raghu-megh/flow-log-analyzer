package com.illumeio.query.strategy.couting;

import com.illumeio.model.V2FlowLogLine;
import com.illumeio.query.ProtocolMapper;
import com.illumeio.query.TagProvider;
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
                    map.putIfAbsent(tag, 0);
                    map.put(tag, map.get(tag) + 1);
                });
    }

    @Override
    public Map<String, Integer> getCount() {
        return map;
    }
}
