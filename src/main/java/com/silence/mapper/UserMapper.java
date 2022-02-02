package com.silence.mapper;

import com.silence.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    void save(User user);

    void saveUserRoleRelation(@Param("userId")Long userId, @Param("roleId")Long roleId);

    void delUserRoleRel(@Param("userId")Long userId);

    void del(@Param("userId")Long userId);

}
