package com.illumeio.query;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class CSVFileTagProvider implements TagProvider {

    private final Map<String, String> lookUpTable;

    public CSVFileTagProvider(CSVReader csvReader) {
        Map<String, String> map = new HashMap<>();

        String[] nextLine;
        try {
            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine[0].startsWith("#")) continue;
                map.put(nextLine[0] + "_" + nextLine[1].toLowerCase(), nextLine[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lookUpTable = Map.copyOf(map);
    }

    @Override
    public Optional<String> getTag(Supplier<String> keyProvider) {
        return Optional.ofNullable(lookUpTable.get(keyProvider.get()))
                .or(() -> Optional.of("untagged"));
    }
}
