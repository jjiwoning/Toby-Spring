package com.example.tobyspring.user.dao.datasource;

import com.example.tobyspring.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

class UserDaoTest {

    @Test
    @DisplayName("추가 및 조회 테스트")
    void addAndGet() throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = ac.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        User user = new User("tamtam", "탐탐", "tamtam");

        userDao.add(user);

        assertThat(userDao.getCount()).isEqualTo(1);

        User findUser = userDao.get("tamtam");

        assertThat(user.getName()).isEqualTo(findUser.getName());
        assertThat(user.getPassword()).isEqualTo(findUser.getPassword());
    }

    @Test
    @DisplayName("getCount 테스트")
    void count() throws SQLException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = ac.getBean("userDao", UserDao.class);

        User user1 = new User("tamtam", "탐탐", "tamtam");
        User user2 = new User("tamtam1", "탐탐1", "tamtam1");
        User user3 = new User("tamtam2", "탐탐2", "tamtam2");

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(user1);
        assertThat(userDao.getCount()).isEqualTo(1);

        userDao.add(user2);
        assertThat(userDao.getCount()).isEqualTo(2);

        userDao.add(user3);
        assertThat(userDao.getCount()).isEqualTo(3);

    }
}