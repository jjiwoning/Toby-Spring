package com.example.tobyspring.user.dao.object_factory;

import com.example.tobyspring.user.dao.separate_class.UserDaoV3;

public class UserDaoTestV1 {
    public static void main(String[] args) {
        UserDaoV3 userDaoV3 = new DaoFactoryV1().userDao();
        UserDaoV3 userDao = new DaoFactoryV2().userDao();
    }
}
