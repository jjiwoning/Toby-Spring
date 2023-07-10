package com.example.tobyspring.user.dao.template.template_method;

import com.example.tobyspring.user.dao.template.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoDeleteAll extends UserDaoAbstract {

    public UserDaoDeleteAll(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * UserDao의 기능을 확장하고 싶을 때 마다 상속을 통한 자유로운 확장 가능
     * 문제점
     * 1. DAO 로직마다 상속을 통해 새로운 클래스를 만들어야 된다. -> 기능이 100개면 클래스 100개
     * 2. 확장 구조가 이미 클래스를 설계하는 시점에서 고정이 된다. -> 컴파일 시점에 관계(변하는 부분과 변하지 않는 부분)가 결정됨 -> 유연성이 떨어진다.
     */
    @Override
    protected PreparedStatement makeStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("delete from users");
    }
}
