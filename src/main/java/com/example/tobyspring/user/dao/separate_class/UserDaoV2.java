package com.example.tobyspring.user.dao.separate_class;

import com.example.tobyspring.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 구체 클래스에 의존하지 않고 인터페이스에 의존하면서 UserDaoV1에서 발생한 문제점 해결 가능
 * 단, 생성자 코드를 수정해야되는 문제점은 여전히 남아있다. -> 구현체를 갈아 끼우려고 하면 생성자 코드를 바꿔줘야한다.
 * -> 이거까지 해결할 수 있는 방법을 관계설정 책임의 분리를 통해 알아보자
 */
public class UserDaoV2 {

    private ConnectionMakerV2 connectionMakerV2;

    public UserDaoV2() {
        this.connectionMakerV2 = new ConnectionMakerV2Impl();
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
}
