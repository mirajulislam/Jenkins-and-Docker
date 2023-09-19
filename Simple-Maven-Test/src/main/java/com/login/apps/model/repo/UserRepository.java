package com.login.apps.model.repo;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.login.apps.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (username, email, password) VALUES (:username, :email, :password)";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", user.getUsername());
        paramMap.put("email", user.getEmail());
        paramMap.put("password", user.getPassword());

        namedParameterJdbcTemplate.update(sql, paramMap);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return namedParameterJdbcTemplate.query(sql, new UserRowMapper());
    }
}

