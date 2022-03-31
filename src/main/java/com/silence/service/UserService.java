package com.silence.service;

import com.silence.DO.UserDO;
import com.silence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserDO getById(Integer id) {
        return userMapper.getById(id);
    }

}
