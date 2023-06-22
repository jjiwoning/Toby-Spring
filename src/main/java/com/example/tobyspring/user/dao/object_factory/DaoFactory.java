package com.example.tobyspring.user.dao.object_factory;

import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2;
import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2Impl;
import com.example.tobyspring.user.dao.separate_class.UserDaoV3;

/**
 * 팩토리로 UserDao와 ConnectionMaker 구현 클래스의 객체를 생성하고 두 객체를 연결하는 역할을 수행한다.
 */
public class DaoFactory {
    public UserDaoV3 userDao() {
        ConnectionMakerV2 connectionMakerV2 = new ConnectionMakerV2Impl();
        return new UserDaoV3(connectionMakerV2);
    }
}
