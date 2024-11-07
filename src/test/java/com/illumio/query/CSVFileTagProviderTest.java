package com.illumio.query;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CSVFileTagProviderTest {

    @Mock
    CSVReader csvReader;

    @Test
    void testSanity() throws IOException {
        when(csvReader.readNext()).thenReturn(new String[]{"22","tcp","sv_P4"}, null);
        CSVFileTagProvider csvFileTagProvider = new CSVFileTagProvider(csvReader);
        assertEquals(Optional.of("sv_P4"), csvFileTagProvider.getTag(() -> "22_tcp"));
    }

    @Test
    void testSanityUntagged() throws IOException {
        when(csvReader.readNext()).thenReturn( null);
        CSVFileTagProvider csvFileTagProvider = new CSVFileTagProvider(csvReader);
        assertEquals(Optional.of("untagged"), csvFileTagProvider.getTag(() -> "22_tcp"));
    }
}