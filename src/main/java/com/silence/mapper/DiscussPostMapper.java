package com.silence.mapper;

import com.silence.DO.DiscussPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPostDO> listPage(@Param("userId") Integer userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    int countRows(@Param("userId") Integer userId);

}
