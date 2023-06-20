package com.example.tobyspring.user.dao.separate_class;

import com.example.tobyspring.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 클래스의 분리: 관심사가 다른 코드를 서로 다른 클래스로 분리
 * 문제점 1: DB 커넥션 확장 기능을 제공할 수 없다. -> simpleConnectionMakerV1라는 구체 클래스에 의존
 * 문제점 2: UserDao의 수정 없이 DB 커넥션 생성 기능을 변경할 수 없음 -> 커넥션 기능이 바뀌면 생성자의 코드를 변경해야 된다.
 * -> 문제의 근본적인 원인: DB 커넥션을 가져오는 클래스의 너무 깊은 정보까지 알고 있다. -> 코드가 구현체에 의존해서 발생한다.
 */
public class UserDaoV1 {

    private SimpleConnectionMakerV1 simpleConnectionMakerV1;

    public UserDaoV1() {
        this.simpleConnectionMakerV1 = new SimpleConnectionMakerV1();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = simpleConnectionMakerV1.makeNewConnection();

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
        Connection conn = simpleConnectionMakerV1.makeNewConnection();

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
