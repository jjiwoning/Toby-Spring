package com.example.tobyspring.user.dao.factory_method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDaoImpl extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.mysql.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/~/tobyspring", "aa", "aa");
    }
}
