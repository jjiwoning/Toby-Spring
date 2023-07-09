package com.example.tobyspring.user.dao.template;

import com.example.tobyspring.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest // 스프링 테스트 적용
@DirtiesContext // 테스트 메서드에서 어플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을 알려주는 역할을 하는 어노테이션
class UserDaoTest {

    @Autowired // 스프링 테스트를 넣어두면 현재 설정 정보를 바탕으로 자동으로 주입됨
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    // BeforeEach를 통해 미리 UserDao를 세팅하여 각 테스트 메서드 별로 UserDao를 가져오는 과정의 중복 코드를 줄일 수 있다.
    // User에 대한 픽스처(테스트를 수행하는데 필요한 정보나 오브젝트)를 미리 만들어둘 수 있다.
    @BeforeEach
    private void setUp() {
        this.user1 = new User("tamtam", "탐탐", "tamtam");
        this.user2 = new User("tamtam1", "탐탐1", "tamtam1");
        this.user3 = new User("tamtam2", "탐탐2", "tamtam2");
    }

    @Test
    @DisplayName("추가 및 조회 테스트")
    void addAndGet() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(user1);

        assertThat(userDao.getCount()).isEqualTo(1);

        User findUser = userDao.get("tamtam");

        assertThat(user1.getName()).isEqualTo(findUser.getName());
        assertThat(user1.getPassword()).isEqualTo(findUser.getPassword());
    }

    @Test
    @DisplayName("getCount 테스트")
    void count() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(user1);
        assertThat(userDao.getCount()).isEqualTo(1);

        userDao.add(user2);
        assertThat(userDao.getCount()).isEqualTo(2);

        userDao.add(user3);
        assertThat(userDao.getCount()).isEqualTo(3);

    }

    @Test
    @DisplayName("get() 예외 테스트")
    void getUserFailure() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        assertThatThrownBy(() -> userDao.get("ex"))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}