package com.silence.controller;

import com.silence.DO.UserDO;
import com.silence.service.FollowService;
import com.silence.util.CommunityUtil;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    @ResponseBody
    public String follow(Integer entityType, Integer entityId) {
        UserDO user = hostHolder.getUser();

        followService.follow(user.getId(), entityType, entityId);

        return CommunityUtil.getJSONString(0, "关注成功！");
    }

    @PostMapping("/unfollow")
    @ResponseBody
    public String unfollow(Integer entityType, Integer entityId) {
        UserDO user = hostHolder.getUser();

        followService.unfollow(user.getId(), entityType, entityId);

        return CommunityUtil.getJSONString(0, "已取消关注！");
    }

}
