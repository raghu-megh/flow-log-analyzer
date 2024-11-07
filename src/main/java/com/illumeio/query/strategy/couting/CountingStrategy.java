package com.illumeio.query.strategy.couting;

import com.illumeio.model.DefaultFlowLogLine;

import java.util.List;
import java.util.Map;

public interface CountingStrategy<X extends DefaultFlowLogLine> {
    void count(List<X> lines);
    void count(X line);
    Map<String, Integer> getCount();
}
