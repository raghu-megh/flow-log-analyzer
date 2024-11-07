package com.illumio;

import com.illumio.model.DefaultFlowLogLine;
import com.illumio.query.strategy.couting.CountingStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FlowLogLineAnalyzer<X extends DefaultFlowLogLine> {

    public void analyze(X line, CountingStrategy<X> strategy) {
        strategy.count(line);
    }
}
