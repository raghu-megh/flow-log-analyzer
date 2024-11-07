package com.illumio.data;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static com.illumio.data.TableCreator.TABLE_NAME;

public class DataCreator {

    public static boolean insert(Map<Integer, String> line) {
        var values = String.join("', '", line.values());

        return DBManager.getConnection()
                .map(connection -> {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute("INSERT INTO " + TABLE_NAME + " VALUES ( '" + values + "' )");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                })
                .orElse(false);
    }

    public static boolean insertAll(List<Map<Integer, String>> lines) {

        var values = lines.stream().map(line -> String.join("', '", line.values()))
                .toList();

        return DBManager.getConnection()
                .map(connection -> {
                    try (Statement statement = connection.createStatement()) {
                        values.forEach(row -> {
                            try {
                                statement.execute("INSERT INTO " + TABLE_NAME + " VALUES ( '" + row + "' )");
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                    return true;
                })
                .orElse(false);
    }
}
