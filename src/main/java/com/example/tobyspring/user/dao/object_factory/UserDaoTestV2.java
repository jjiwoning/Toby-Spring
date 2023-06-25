package com.example.tobyspring.user.dao.object_factory;

import com.example.tobyspring.user.dao.separate_class.UserDaoV3;
import com.example.tobyspring.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTestV2 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactoryV3.class);
        UserDaoV3 userDao = ac.getBean("userDaoV3", UserDaoV3.class);

        User user = new User();
        user.setId("tamtam");
        user.setName("탐탐");
        user.setPassword("tamtam");

        userDao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User findUser = userDao.get("tamtam");
        System.out.println(findUser.getId());
        System.out.println(findUser.getName());
        System.out.println(findUser.getPassword());

        System.out.println(findUser.getId() + " 조회 성공");
    }

}
