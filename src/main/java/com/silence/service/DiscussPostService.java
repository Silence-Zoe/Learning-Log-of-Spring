package com.silence.service;

import com.silence.DO.DiscussPostDO;
import com.silence.mapper.DiscussPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPostDO> listPage(Integer userId, int pageNum, int pageSize) {
        return discussPostMapper.listPage(userId, pageNum, pageSize);
    }

    public int countRows(Integer userId) {
        return discussPostMapper.countRows(userId);
    }

}
