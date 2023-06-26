package com.example.tobyspring.user.dao.singleton;

import com.example.tobyspring.user.dao.separate_class.ConnectionMakerV2;
import com.example.tobyspring.user.domain.User;

import java.sql.Connection;

/**
 * 싱글톤은 멀티스레드 환경을 주의해야 한다. -> 동시성 이슈 발생 가능
 * 클래스가 상태를 가지게 설계하면 안된다. -> 클래스 필드에 변수를 두는 것을 권하지 않는다. (무조건 동시성 이슈 발생)
 * 동시성 이슈를 막기 위해 스택 영역인 메서드에 변수(파라미터, 메서드 로컬 변수)를 넣어서 사용하자
 */
public class UserDaoStateless {

    // ConnectionMaker는 읽기 전용이므로 클래스 필드에 두어도 상관없다. + 스프링 빈에 등록되므로 싱글톤
    private ConnectionMakerV2 connectionMakerV2;

    // 이런 클래스 필드 변수를 사용하면 안된다. -> 싱글톤이기 때문에 쓰레드 간 힙 영역을 공유하여 반드시 동시성 이슈가 생김
    private Connection connection;

    private User user;

    // 후속 코드 생략
}
