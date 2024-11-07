package com.illumio.data;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class DBManager {

    private static final Connection CONNECTION = initialize();

    private static Connection initialize() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            return connection;
        }

        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test");
        } catch (Exception e) {
            return connection;
        }

        return connection;
    }

    public static Optional<Connection> getConnection() {
        return Optional.ofNullable(CONNECTION);
    }

    public static void closeConnection() {
        getConnection().ifPresent(connection -> {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        });

        System.out.println("Connection closed");
    }

    public static Optional<Statement> createStatement() {
        return getConnection()
                .map(connection -> {
                    Statement stat;
                    try {
                        stat = connection.createStatement();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    return stat;
                });
    }

    public static void closeStatement(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
