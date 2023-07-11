package com.example.tobyspring.user.dao.template.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makePrepareStatement(Connection c) throws SQLException;
}
