package com.example.tobyspring.user.dao.template.class_separate;

import com.example.tobyspring.user.dao.template.strategy.DeleteAllStatement;
import com.example.tobyspring.user.dao.template.strategy.StatementStrategy;
import com.example.tobyspring.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoClassSeparate {

    private final JdbcContext jdbcContext;

    public UserDaoClassSeparate(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void add(final User user) throws SQLException {
        jdbcContext.workWithStatementStrategy(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement("insert into users(id, name, password) values ?, ?, ?");
                    ps.setString(1, user.getId());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());

                    return ps;
                }
        );
    }

    public void deleteAll() throws SQLException {
        jdbcContext.workWithStatementStrategy(
                connection -> connection.prepareStatement("delete from users")
        );
    }

}
