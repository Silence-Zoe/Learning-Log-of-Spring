package com.silence.mapper;

import com.silence.DO.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserDO getById(@Param("id")Integer id);

    UserDO getByName(@Param("username") String username);

    UserDO getByEmail(@Param("email") String email);

    int save(UserDO user);

    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    int updateHeaderUrlById(@Param("id") Integer id, @Param("headerUrl") String headerUrl);

    int updateUserPasswordById(@Param("id") Integer id, @Param("password") String password);
}
