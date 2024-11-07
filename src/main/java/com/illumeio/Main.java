package com.illumeio;

import com.illumeio.model.V2FlowLogLine;
import com.illumeio.parser.V2FlowLogParser;
import com.illumeio.query.StaticProtocolMapper;
import com.illumeio.query.CSVFileTagProvider;
import com.illumeio.query.output.ActionCountOutputFormatter;
import com.illumeio.query.output.PortProtocolCountOutputFormatter;
import com.illumeio.query.output.TagCountOutputFormatter;
import com.illumeio.query.strategy.couting.ActionCountStrategy;
import com.illumeio.query.strategy.couting.SourcePortProtocolStrategy;
import com.illumeio.query.strategy.couting.V2TagCountStrategy;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        var url = classloader.getResource("V2-flow-log.log");

        assert url != null;
        List<V2FlowLogLine> lines = new V2FlowLogParser().parse(new FileReader(url.getFile()));

        url = classloader.getResource("lookup-table.csv");
        assert url != null;
        var tagCount = new V2TagCountStrategy(new CSVFileTagProvider(
                new CSVReader(new FileReader(url.getFile()))),
                new StaticProtocolMapper());
        tagCount.count(lines);
        new TagCountOutputFormatter().write(new PrintWriter(System.out, true), tagCount.getCount());
        //Directing the result to a file
        new TagCountOutputFormatter().write(new FileWriter("sampleOutput.txt"), tagCount.getCount());

        //Count the actions
        var actionCount = new ActionCountStrategy();
        actionCount.count(lines);
        new ActionCountOutputFormatter().write(new PrintWriter(System.out, true), actionCount.getCount());

        var portProtocolCount = new SourcePortProtocolStrategy(new StaticProtocolMapper());
        portProtocolCount.count(lines);
        new PortProtocolCountOutputFormatter().write(new PrintWriter(System.out, true), portProtocolCount.getCount());
    }
}