package com.silence.mapper;

import com.silence.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> findAll();

    void save(Role role);

    List<Role> findRoleByUserId(@Param("id")Long id);
}
