package com.example.tobyspring.user.dao.template.strategy;

import com.example.tobyspring.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 익명 내부 클래스를 활용하여 전략 클래스를 줄이고 코드까지 줄이는 방법
 */
public class UserDaoStrategyV4 {
    private DataSource dataSource;

    public UserDaoStrategyV4(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(final User user) throws SQLException {
        jdbcContextWithStatementStrategy(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement("insert into users(id, name, password) values ?, ?, ?");
                    ps.setString(1, user.getId());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getPassword());

                    return ps;
                }
        );
    }

    public User get(String id) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(
                "select * from users where id = ?"
        );
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        }

        rs.close();
        pstmt.close();
        conn.close();

        if (user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return user;
    }

    public void deleteAll() throws SQLException {
        jdbcContextWithStatementStrategy(
                connection -> {
                    connection.prepareStatement("delete from users")
                }
        );
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        // try - catch - finally 구문으로 예외처리를 추가해준다.
        try {
            conn = dataSource.getConnection();
            pstmt = stmt.makePrepareStatement(conn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public int getCount() throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement pstmt = conn.prepareStatement("select count(*) from users");

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        pstmt.close();
        conn.close();

        return count;
    }
}
