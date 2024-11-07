package com.illumio.query.strategy.couting;

import com.illumio.model.V2FlowLogLine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionCountStrategy implements CountingStrategy<V2FlowLogLine> {
    private final Map<String, Integer> map = new HashMap<>();

    @Override
    public void count(List<V2FlowLogLine> lines) {
        lines.forEach(this::count);
    }

    @Override
    public void count(V2FlowLogLine line) {
        map.put(line.getAction(), map.getOrDefault(line.getAction(), 0) + 1);
    }

    @Override
    public Map<String, Integer> getCount() {
        return map;
    }
}
