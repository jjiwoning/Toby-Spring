package com.example.tobyspring.user.dao.object_factory;

import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2;
import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2Impl;
import com.example.tobyspring.user.dao.separate_class.UserDaoV3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring의 Bean Factory가 사용할 수 있는 설정 정보
 */
@Configuration
public class DaoFactoryV3 {

    @Bean
    public UserDaoV3 userDao() {
        return new UserDaoV3(connectionMaker());
    }

    @Bean
    public ConnectionMakerV2 connectionMaker() {
        return new ConnectionMakerV2Impl();
    }

}
