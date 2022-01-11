package com.silence.dao.impl;

import com.silence.dao.RoleDao;
import com.silence.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = jdbcTemplate.query("SELECT * FROM sys_role", new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    @Override
    public void save(Role role) {
        jdbcTemplate.update("INSERT INTO sys_role VALUES(?, ?, ?)", null, role.getRoleName(), role.getRoleDesc());
    }

    @Override
    public List<Role> findRoleByUserId(Long id) {
        List<Role> roles = jdbcTemplate.query("SELECT * FROM sys_user_role AS ur, sys_role AS r WHERE ur.roleId = r.id AND ur.userId = ?",new BeanPropertyRowMapper<Role>(Role.class), id);
        return roles;
    }
}
