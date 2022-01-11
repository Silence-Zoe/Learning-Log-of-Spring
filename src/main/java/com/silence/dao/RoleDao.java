package com.silence.dao;

import com.silence.domain.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAll();
    void save(Role role);
}
