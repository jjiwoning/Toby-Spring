package com.example.tobyspring.user.dao.singleton;

import com.example.tobyspring.user.dao.object_factory.DaoFactoryV2;
import com.example.tobyspring.user.dao.separate_class.UserDaoV3;

/**
 * 기존 Factory에서 꺼내오는 방식: 두 번 호출할 때 두 객체가 동일한 객체가 아니다 -> 호출 마다 new로 생성되기 때문에
 */
public class SingletonTestV1 {
    public static void main(String[] args) {
        DaoFactoryV2 daoFactory = new DaoFactoryV2();
        UserDaoV3 userDao1 = daoFactory.userDao();
        UserDaoV3 userDao2 = daoFactory.userDao();

        System.out.println(userDao1);
        System.out.println(userDao2);
        System.out.println(userDao1 == userDao2);
    }
}
