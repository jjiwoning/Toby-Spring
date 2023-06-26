package com.example.tobyspring.user.dao.singleton;

import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2;

/**
 * 싱글톤 패턴으로 만든 UserDao
 * 싱글톤 패턴의 단점
 * 1. private 생성자로 인해 상속이 불가능하다. -> 다형성 적용 불가
 * 2. 테스트하기 어려움 -> Mock 객체 등으로 대체가 어려움
 * 3. 서버 환경에서 싱글톤이 하나만 만들어짐을 보장하지 못한다.
 * 4. 싱글톤은 전역 상태를 만들 수 있어 바람직하지 못하다
 * -> 스프링의 싱글톤 레지스트리는 이러한 싱글톤 패턴의 단점을 보완하여 싱글톤 객체를 제공한다.
 * -> 평범한 자바 클래스를 싱글톤 객체로 만들어준다.
 */
public class UserDaoSingleton {

    private static UserDaoSingleton INSTANCE;

    private ConnectionMakerV2 connectionMaker;

    private UserDaoSingleton(ConnectionMakerV2 connectionMakerV2) {
        this.connectionMaker = connectionMakerV2;
    }

    // 동시성 이슈가 있을 수 있으므로 synchronized 키워드로 동시성 이슈 막아둬야 함
    public static synchronized UserDaoSingleton getInstance() {
        if (INSTANCE == null) {
//            INSTANCE = new UserDaoSingleton(???); -> ConnectionMaker를 넣어주는게 불가능하다.
        }
        return INSTANCE;
    }

    // userDao 로직 생략
}
