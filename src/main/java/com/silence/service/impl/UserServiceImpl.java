package com.silence.service.impl;

import com.silence.domain.Role;
import com.silence.domain.User;
import com.silence.mapper.RoleMapper;
import com.silence.mapper.UserMapper;
import com.silence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<User> list() {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            Long id = user.getId();
            List<Role> roles = roleMapper.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        userMapper.save(user);
        for (Long roleId : roleIds) {
            userMapper.saveUserRoleRelation(user.getId(), roleId);
        }
    }

    @Override
    public void del(Long userId) {
        userMapper.delUserRoleRel(userId);
        userMapper.del(userId);
    }
}
