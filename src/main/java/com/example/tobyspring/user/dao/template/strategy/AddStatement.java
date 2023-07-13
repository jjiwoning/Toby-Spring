package com.example.tobyspring.user.dao.template.strategy;

import com.example.tobyspring.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy {

    private final User user;

    public AddStatement(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makePrepareStatement(Connection c) throws SQLException {
        PreparedStatement pstmt = c.prepareStatement(
                "insert into users(id, name, password) values (?, ?, ?)"
        );

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        return pstmt;
    }
}
