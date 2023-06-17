package com.example.tobyspring.user.dao;

import com.example.tobyspring.user.domain.User;

import java.sql.*;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/tobyspring", "sa", "");

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

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/tobyspring", "sa", "");

        PreparedStatement pstmt = conn.prepareStatement(
                "select * from users where id = ?"
        );
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }

    // 테스트 코드
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

        User user = new User();
        user.setId("tamtam");
        user.setName("탐탐");
        user.setPassword("tamtam");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User findUser = userDao.get("tamtam");
        System.out.println(findUser.getId());
        System.out.println(findUser.getName());
        System.out.println(findUser.getPassword());

        System.out.println(findUser.getId() + " 조회 성공");
    }
}
