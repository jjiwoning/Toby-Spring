package com.example.tobyspring.user.dao.datasource;

import com.example.tobyspring.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDaoTest {

    @Test
    @DisplayName("추가 및 조회 테스트")
    void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = ac.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("tamtam");
        user.setName("탐탐");
        user.setPassword("tamtam");

        userDao.add(user);

        User findUser = userDao.get("tamtam");

        assertThat(user.getName()).isEqualTo(findUser.getName());
        assertThat(user.getPassword()).isEqualTo(findUser.getPassword());
    }

}