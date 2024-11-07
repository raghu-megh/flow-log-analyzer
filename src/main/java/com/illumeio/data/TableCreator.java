package com.illumeio.data;

import java.sql.Statement;

public class TableCreator {

    public static final String TABLE_NAME = " FLOW_LOG_LINE ";

    public static boolean createTable() {
        return DBManager.getConnection()
                .map(connection -> {
                    try(Statement statement = connection.createStatement()) {
                        statement.execute("DROP TABLE " + TABLE_NAME);
                        statement.execute("CREATE TABLE " + TABLE_NAME + " (" +
                                "version VARCHAR(2)," +
                                "account_id VARCHAR(20)," +
                                "interface_id VARCHAR(20)," +
                                "source_address VARCHAR(25)," +
                                "destination_address VARCHAR(25)," +
                                "source_port VARCHAR(6)," +
                                "destination_port VARCHAR(6)," +
                                "protocol VARCHAR(4)," +
                                "packets_count VARCHAR(5), " +
                                "bytes_count VARCHAR(5), " +
                                "start_time VARCHAR(10), " +
                                "end_time VARCHAR(10), " +
                                "action VARCHAR(6), " +
                                "log_status VARCHAR(8)" +
                                ")");

                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                }).orElse(false);
    }
}
