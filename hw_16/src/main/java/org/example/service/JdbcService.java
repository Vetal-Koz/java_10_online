package org.example.service;

import java.sql.Connection;

public interface JdbcService {
    Connection getConnection();
}
