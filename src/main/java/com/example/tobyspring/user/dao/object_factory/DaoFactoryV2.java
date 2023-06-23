package com.example.tobyspring.user.dao.object_factory;

import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2;
import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2Impl;
import com.example.tobyspring.user.dao.separate_class.UserDaoV3;

/**
 * 기존의 DaoFactoryV1의 개선: ConnectionMaker 코드의 중복을 제거하기 위해 ConnectionMaker 생성용 메서드를 만들고 이를 이용
 */
public class DaoFactoryV2 {

    public UserDaoV3 userDao() {
        return new UserDaoV3(connectionMaker());
    }

    /**
     * ConnectionMaker 객체를 생성하는 메서드를 새로 생성함으로 다른 Dao에서 해당 메서드를 이용하여 코드의 유지보수성을 향상시킬 수 있다.
     */
    public ConnectionMakerV2 connectionMaker() {
        return new ConnectionMakerV2Impl();
    }
}
