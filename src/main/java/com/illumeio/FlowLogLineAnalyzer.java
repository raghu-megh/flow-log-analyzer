package com.illumeio;

import com.illumeio.model.DefaultFlowLogLine;
import com.illumeio.query.strategy.couting.CountingStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FlowLogLineAnalyzer<X extends DefaultFlowLogLine> {

    public void analyze(X line, CountingStrategy<X> strategy) {
        strategy.count(line);
    }
}
