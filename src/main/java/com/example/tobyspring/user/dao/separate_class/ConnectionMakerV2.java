package com.example.tobyspring.user.dao.separate_class;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 인터페이스 도입
 */
public interface ConnectionMakerV2 {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}
