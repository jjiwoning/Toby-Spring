package com.example.tobyspring.user.dao.object_factory;

import com.example.tobyspring.user.dao.separate_class.UserDaoV3;

public class UserDaoTest {
    public static void main(String[] args) {
        UserDaoV3 userDaoV3 = new DaoFactory().userDao();
    }
}
