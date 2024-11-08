package com.illumio.parser;

import com.illumio.model.V2FlowLogLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class V2FlowLogParser implements FlowLogParser<V2FlowLogLine> {

    private static final String V2_FLOW_LOG_LINE_REGEX = "^2\\s+(\\w++)\\s+(\\S++)\\s+(\\S++)\\s+(\\S++)\\s+(\\d++)\\s+(\\d++)\\s+(\\d++)\\s+(\\d++)\\s+(\\d++)\\s+(\\d++)\\s+(\\d++)\\s+(ACCEPT|REJECT)\\s+(OK|NODATA|SKIPDATA)\\s*";
    private static final Pattern PATTERN = Pattern.compile(V2_FLOW_LOG_LINE_REGEX);

    private static final int V2_FIELDS_COUNT = 13;   //without the version field

    @Override
    public List<V2FlowLogLine> parse(Reader reader) {
        BufferedReader bufferedReader = new BufferedReader(reader);
        List<V2FlowLogLine> list = new ArrayList<>();

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parse(line)
                        .ifPresent(list::add);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Optional<V2FlowLogLine> parse(String line) {
        return Optional.ofNullable(line)
                .map(l -> {
                    Matcher m = PATTERN.matcher(l.trim());
                    return !(m.matches() && m.groupCount() == V2_FIELDS_COUNT) ?
                            null :
                            V2FlowLogLine.builder()
                                    .bytes(Integer.parseInt(m.group(BYTES_COUNT).trim()))
                                    .action(m.group(ACTION).trim())
                                    .accountId(m.group(ACCOUNT_ID).trim())
                                    .endTime(Integer.parseInt(m.group(END_TIME).trim()))
                                    .startTime(Integer.parseInt(m.group(START_TIME).trim()))
                                    .logStatus(m.group(LOG_STATUS).trim())
                                    .protocolNumber(Integer.parseInt(m.group(PROTOCOL).trim()))
                                    .packets(Integer.parseInt(m.group(PACKETS_COUNT).trim()))
                                    .destinationPort(m.group(DESTINATION_PORT).trim())
                                    .destinationAddress(m.group(DESTINATION_ADDRESS).trim())
                                    .sourceAddress(m.group(SOURCE_ADDRESS).trim())
                                    .interfaceId(m.group(INTERFACE_ID).trim())
                                    .sourcePort(m.group(SOURCE_PORT).trim())
                                    .build();
                });
    }
}
