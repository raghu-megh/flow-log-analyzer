package com.illumeio.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableCreatorTest {

    @Test
    void tableCreatedSuccessfully() {
        boolean flag = TableCreator.createTable();
        assertTrue(flag);
    }

}