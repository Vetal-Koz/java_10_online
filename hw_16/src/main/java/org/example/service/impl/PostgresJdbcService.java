package org.example.service.impl;

import org.example.service.JdbcService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresJdbcService implements JdbcService {

    private Connection connection;

    public PostgresJdbcService(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
