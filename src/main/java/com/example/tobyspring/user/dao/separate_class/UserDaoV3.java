package com.example.tobyspring.user.dao.separate_class;

import com.example.tobyspring.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 관계 설정 책임의 분리
 */
public class UserDaoV3 {

    private ConnectionMakerV2 connectionMakerV2;

    // 객체 간의 관계를 맺는 책임을 UserDao의 클라이언트에게 넘겨준다.
    public UserDaoV3(ConnectionMakerV2 connectionMakerV2) {
        this.connectionMakerV2 = connectionMakerV2;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMakerV2.makeConnection();

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
        Connection conn = connectionMakerV2.makeConnection();

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
    public static void main(String[] args) {
        // userDao 코드 변경없이 ConnectionMaker 를 갈아 끼울 수 있게 된다.
        UserDaoV3 userDao = new UserDaoV3(new ConnectionMakerV2Impl());
    }
}
