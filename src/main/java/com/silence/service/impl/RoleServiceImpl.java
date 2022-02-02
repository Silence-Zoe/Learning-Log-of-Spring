package com.silence.service.impl;

import com.silence.domain.Role;
import com.silence.mapper.RoleMapper;
import com.silence.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> list() {
        return roleMapper.findAll();
    }

    @Override
    public void save(Role role) {
        roleMapper.save(role);
    }
}
