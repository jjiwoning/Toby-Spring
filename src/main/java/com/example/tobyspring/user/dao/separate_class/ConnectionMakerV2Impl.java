package com.example.tobyspring.user.dao.separate_class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMakerV2Impl implements ConnectionMakerV2{
    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/tobyspring", "sa", "");
    }
}
