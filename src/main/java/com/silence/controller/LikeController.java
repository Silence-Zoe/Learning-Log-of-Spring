package com.silence.controller;

import com.silence.DO.UserDO;
import com.silence.service.LikeService;
import com.silence.util.CommunityUtil;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LikeController {

    @Autowired
    LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/like")
    @ResponseBody
    public String like(Integer entityType, Integer entityId, Integer entityUserId) {
        UserDO user = hostHolder.getUser();

        likeService.like(user.getId(), entityType, entityId, entityUserId);

        long likeCount = likeService.getEntityLikeCount(entityType, entityId);
        int likeStatus = likeService.getEntityLikeStatus(user.getId(), entityType, entityId);

        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);
        return CommunityUtil.getJSONString(0, null, map);
    }
}
