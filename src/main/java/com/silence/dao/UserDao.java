package com.silence.dao;

import com.silence.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
