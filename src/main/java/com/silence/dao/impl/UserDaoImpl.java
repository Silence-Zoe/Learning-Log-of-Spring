package com.silence.dao.impl;

import com.silence.dao.UserDao;
import com.silence.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query("SELECT * FROM sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }
}
