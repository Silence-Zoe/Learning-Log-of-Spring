package com.silence.mapper;

import com.silence.DO.DiscussPostDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    List<DiscussPostDO> listPage(@Param("userId") Integer userId, @Param("offSet") int offSet, @Param("pageSize") int pageSize, @Param("orderMode") int orderMode);

    int countRows(@Param("userId") Integer userId);

    int saveDiscussPost(DiscussPostDO discussPost);

    DiscussPostDO getById(@Param("id") Integer id);

    int updateCommentCount(@Param("id") Integer id, @Param("commentCount") Integer commentCount);

    int updateTypeById(@Param("id") Integer id, @Param("type") Integer type);

    int updateStatusById(@Param("id") Integer id, @Param("status") Integer status);

    int updateScoreById(@Param("id") Integer id, @Param("score") Double score);

}
