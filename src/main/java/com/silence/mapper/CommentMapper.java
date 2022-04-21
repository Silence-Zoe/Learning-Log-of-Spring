package com.silence.mapper;

import com.silence.DO.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDO> listPageByEntity(@Param("entityType") Integer entityType, @Param("entityId") Integer entityId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    int countRows(@Param("entityType") Integer entityType, @Param("entityId") Integer entityId);

    int saveComment(CommentDO comment);

    CommentDO getById(@Param("id") Integer id);

}
