package paysecure.common.db.mysql.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paysecure.common.db.mysql.mapper.LoginMapper;
import paysecure.common.db.mysql.model.AuthLogin;

@Repository
public class AuthLoginDao {

    private static final String SQL_FIND_AUTH_LOGIN_BY_NAME = "select * from auth_login a, login_type l where a.login_type = l.type_id and login_name = ?";

    private final JdbcTemplate jdbcTemplate;

    public AuthLoginDao(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AuthLogin getLoginByName(String username) {
        try {
            return jdbcTemplate.queryForObject(
                    SQL_FIND_AUTH_LOGIN_BY_NAME,
                    new Object[]{username},
                    new LoginMapper()
            );
        } catch (Exception e) {
            return null; // return null if not found
        }
    }
}
