package com.silence.service.impl;

import com.silence.dao.RoleDao;
import com.silence.dao.UserDao;
import com.silence.domain.Role;
import com.silence.domain.User;
import com.silence.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            Long id = user.getId();
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }
        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        Long userId = userDao.save(user);
        userDao.saveUserRoleRelation(userId, roleIds);
    }

    @Override
    public void del(Long userId) {
        userDao.delUserRoleRel(userId);
        userDao.del(userId);
    }
}
