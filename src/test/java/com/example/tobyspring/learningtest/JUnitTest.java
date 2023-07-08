package com.example.tobyspring.learningtest;

import com.example.tobyspring.user.dao.datasource.DaoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@Configuration
class EmptyConfiguration {
}

/**
 * 테스트의 목적
 * 1. JUnit이 테스트를 실행할 때 매번 새로운 테스트 오브젝트를 생성하는지 확인 가능
 * 2. Autowired로 주입되는 context 변수가 항상 같은 오브젝트인지 확인 가능
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoFactory.class)
public class JUnitTest {

    @Autowired
    ApplicationContext ac;

    static Set<JUnitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    public void test1() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        System.out.println(ac);
        System.out.println(contextObject);

        assertThat(contextObject == null || contextObject == this.ac).isTrue();
        contextObject = this.ac;
    }

    @Test public void test2() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        System.out.println(ac);
        System.out.println(contextObject);

        assertThat(contextObject == null || contextObject == this.ac).isTrue();
        contextObject = this.ac;
    }

    @Test public void test3() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        System.out.println(ac);
        System.out.println(contextObject);

        assertThat(contextObject == null || contextObject == this.ac).isTrue();
        contextObject = this.ac;
    }
}
