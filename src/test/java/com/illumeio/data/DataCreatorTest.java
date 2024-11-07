package com.illumeio.data;

import com.illumeio.parser.V2FlowLogParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataCreatorTest {

    @Test
    void dataCreatorTest() {
        boolean flag = TableCreator.createTable();
        assertTrue(flag);

        String line = "2 123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK";

       // flag = DataCreator.insert(new V2FlowLogParser().parse(line));
        assertTrue(flag);
    }

    @Test
    void dataCreatorBatchTest() {
        boolean flag = TableCreator.createTable();
        assertTrue(flag);

        String line1 = "2 123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK";
        String line2 = "2 123456789012 eni-0a1b2c3d 10.0.1.201 198.51.100.2 443 49153 6 25 20000 1620140761 1620140821 ACCEPT OK";

        //flag = DataCreator.insertAll(List.of(new V2FlowLogParser().parse(line1), new V2FlowLogParser().parse(line2)));
        assertTrue(flag);
    }

}