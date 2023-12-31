package com.example.tobyspring.user.dao.template;

import com.example.tobyspring.user.dao.template.class_separate.JdbcContext;
import com.example.tobyspring.user.dao.template.class_separate.UserDaoClassSeparate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/tobyspring");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public UserDaoClassSeparate userDaoClassSeparate() {
        return new UserDaoClassSeparate(jdbcContext());
    }

    @Bean
    public JdbcContext jdbcContext() {
        return new JdbcContext(dataSource());
    }
}
