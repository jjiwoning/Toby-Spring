package com.example.tobyspring.user.dao.separate_class;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 인터페이스 도입
 * -> 인터페이스를 도입함으로 ConnectionMaker를 사용하는 클래스가 어떤 기능을 사용하겠다에만 집중하고 어떻게 구현되었는지에 대해 집중하지 않아도 된다.
 */
public interface ConnectionMakerV2 {
    Connection makeConnection() throws ClassNotFoundException, SQLException;
}
