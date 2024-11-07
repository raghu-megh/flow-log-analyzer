package com.illumio.query.strategy;

import com.illumio.model.V2FlowLogLine;
import com.illumio.query.StaticProtocolMapper;
import com.illumio.query.CSVFileTagProvider;
import com.illumio.query.strategy.couting.V2TagCountStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class V2TagCountStrategyTest {

    @Mock
    CSVFileTagProvider CSVFileTagProvider;

    @Test
    void sanityCheck() throws FileNotFoundException {
        when(CSVFileTagProvider.getTag(any())).thenReturn(Optional.of("sv_p2"));
        var utt = new V2TagCountStrategy(CSVFileTagProvider, new StaticProtocolMapper());

        utt.count(List.of(
                V2FlowLogLine.builder()
                        .destinationPort("443")
                        .protocolNumber(6)
                        .build(),
                V2FlowLogLine.builder()
                        .destinationPort("443")
                        .protocolNumber(6)
                        .build()
        ));

        var map = utt.getCount();
        assertEquals(1, map.size());
        assertEquals(2, map.get("sv_p2"));
    }
}