package com.example.tobyspring.user.dao.template.template_method;

import com.example.tobyspring.user.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDaoAbstract {

    private DataSource dataSource;

    public UserDaoAbstract(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement pstmt = conn.prepareStatement(
                "insert into users(id, name, password) values (?, ?, ?)"
        );

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
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

    /*
    예외가 발생했을 때 자원을 반환해주지 않으면 시스템에 큰 문제를 일으킨다.
    -> 이 부분에 대한 코드를 추가해야한다.
    이 방식의 문제점: UserDao에 있는 모든 메서드에 예외 처리 코드가 들어가 코드가 매우 복잡해진다.
    -> 분리를 위한 디자인 패턴 적용이 필요하다. (반복되는 코드와 바뀌는 코드를 분리해야된다.)
     */
    public void deleteAll() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        // try - catch - finally 구문으로 예외처리를 추가해준다.
        try {
            conn = dataSource.getConnection();
            pstmt = makeStatement(conn); // 메서드 추출 -> 분리시킨 메서드를 다른 곳에서 재사용할 수 없다.
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

    abstract protected PreparedStatement makeStatement(Connection connection) throws SQLException;

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
