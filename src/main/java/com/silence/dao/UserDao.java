package com.silence.dao;

import com.silence.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    Long save(User user);

    void saveUserRoleRelation(Long id, Long[] roleIds);

    void delUserRoleRel(Long userId);

    void del(Long userId);
}
