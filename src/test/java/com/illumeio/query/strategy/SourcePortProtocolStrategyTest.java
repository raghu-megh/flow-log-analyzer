package com.illumeio.query.strategy;

import com.illumeio.model.V2FlowLogLine;
import com.illumeio.query.StaticProtocolMapper;
import com.illumeio.query.strategy.couting.SourcePortProtocolStrategy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SourcePortProtocolStrategyTest {

    @Test
    void count() {
        var utt = new SourcePortProtocolStrategy(new StaticProtocolMapper());

        utt.count(List.of(
                V2FlowLogLine.builder()
                        .sourcePort("443")
                        .protocolNumber(6)
                        .build(),
                V2FlowLogLine.builder()
                        .sourcePort("443")
                        .protocolNumber(6)
                        .build()
        ));

        var map = utt.getCount();
        assertEquals(1, map.size());
    }
}