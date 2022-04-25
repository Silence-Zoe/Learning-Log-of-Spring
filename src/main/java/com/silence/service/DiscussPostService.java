package com.silence.service;

import com.silence.DO.DiscussPostDO;
import com.silence.mapper.DiscussPostMapper;
import com.silence.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<DiscussPostDO> listPage(Integer userId, int offSet, int pageSize, int orderMode) {
        return discussPostMapper.listPage(userId, offSet, pageSize, orderMode);
    }

    public int countRows(Integer userId) {
        return discussPostMapper.countRows(userId);
    }

    public int addDiscussPost(DiscussPostDO discussPost) {
        if (discussPost == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }

        discussPost.setTitle(HtmlUtils.htmlEscape(discussPost.getTitle()));
        discussPost.setContent(HtmlUtils.htmlEscape(discussPost.getContent()));
        discussPost.setTitle(sensitiveFilter.filter(discussPost.getTitle()));
        discussPost.setContent(sensitiveFilter.filter(discussPost.getContent()));
        discussPost.setCommentCount(0);
        discussPost.setScore(0d);

        return discussPostMapper.saveDiscussPost(discussPost);
    }

    public DiscussPostDO getById(Integer id) {
        return discussPostMapper.getById(id);
    }

    public int updateCommentCount(Integer id, Integer commentCount) {
        return discussPostMapper.updateCommentCount(id, commentCount);
    }

    public int updateType(Integer id, Integer type) {
        return discussPostMapper.updateTypeById(id, type);
    }

    public int updateStatus(Integer id, Integer status) {
        return discussPostMapper.updateStatusById(id, status);
    }

    public int updateScore(Integer id, Double score) {
        return discussPostMapper.updateScoreById(id, score);
    }

}
