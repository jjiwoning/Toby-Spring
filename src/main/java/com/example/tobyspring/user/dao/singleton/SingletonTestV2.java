package com.example.tobyspring.user.dao.singleton;

import com.example.tobyspring.user.dao.object_factory.DaoFactoryV3;
import com.example.tobyspring.user.dao.separate_class.UserDaoV3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 스프링 컨텍스트에서 가져오는 경우: 두 번 호출해도 둘이 같은 객체이다. -> 스프링 컨테이너에 객체를 1개만 등록하기 때문에
 * 싱글톤을 사용하는 이유: 요청마다 객체를 생성하게 되면 서버에 엄청난 부담이 될 수 있어 기본적으로 싱글톤으로 객체를 생성한다.
 */
public class SingletonTestV2 {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactoryV3.class);

        UserDaoV3 userDao1 = ac.getBean("userDaoV3", UserDaoV3.class);
        UserDaoV3 userDao2 = ac.getBean("userDaoV3", UserDaoV3.class);

        System.out.println(userDao1);
        System.out.println(userDao2);
        System.out.println(userDao1 == userDao2);
    }
}
