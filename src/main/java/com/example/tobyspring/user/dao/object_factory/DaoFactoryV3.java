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
    public UserDaoV3 userDaoV3() {
        return new UserDaoV3(connectionMakerV2());
    }

    @Bean
    public ConnectionMakerV2 connectionMakerV2() {
        return new ConnectionMakerV2Impl();
    }

}
