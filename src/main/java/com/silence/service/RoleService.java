package com.silence.service;

import com.silence.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> list();

    void save(Role role);
}
