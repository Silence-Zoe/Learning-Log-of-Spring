package com.silence.service;

import com.silence.DO.CommentDO;
import com.silence.mapper.CommentMapper;
import com.silence.util.CommunityConstant;
import com.silence.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentService implements CommunityConstant {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;

    public List<CommentDO> listPageByEntity(Integer entityType, Integer entityId, int offset, int pageSize) {
        return commentMapper.listPageByEntity(entityType, entityId, offset, pageSize);
    }

    public int countRows(Integer entityType, Integer entityId) {
        return commentMapper.countRows(entityType, entityId);
    }

    public int addComment(CommentDO comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }

        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        int rows = commentMapper.saveComment(comment);

        if (comment.getEntityType() == ENTITY_TYPE_COMMENT) {
            int count = commentMapper.countRows(comment.getEntityType(), comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(), count);
        }

        return rows;
    }

    public CommentDO getById(Integer id) {
        return commentMapper.getById(id);
    }

}
