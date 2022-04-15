package com.silence.service;

import com.silence.DO.CommentDO;
import com.silence.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDO> listPageByEntity(Integer entityType, Integer entityId, int offset, int pageSize) {
        return commentMapper.listPageByEntity(entityType, entityId, offset, pageSize);
    }

    public int countRows(Integer entityType, Integer entityId) {
        return commentMapper.countRows(entityType, entityId);
    }

}
