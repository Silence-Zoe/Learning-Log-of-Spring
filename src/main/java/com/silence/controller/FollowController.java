package com.silence.controller;

import com.silence.DO.UserDO;
import com.silence.DTO.EventDTO;
import com.silence.DTO.PageDTO;
import com.silence.event.EventProducer;
import com.silence.service.FollowService;
import com.silence.service.UserService;
import com.silence.util.CommunityConstant;
import com.silence.util.CommunityUtil;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class FollowController implements CommunityConstant {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private FollowService followService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventProducer eventProducer;

    @PostMapping("/follow")
    @ResponseBody
    public String follow(Integer entityType, Integer entityId) {
        UserDO user = hostHolder.getUser();

        followService.follow(user.getId(), entityType, entityId);

        EventDTO event = new EventDTO()
                .setTopic(TOPIC_FOLLOW)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(entityType)
                .setEntityId(entityId)
                .setEntityUserId(entityId);
        eventProducer.fireEvent(event);

        return CommunityUtil.getJSONString(0, "关注成功！");
    }

    @PostMapping("/unfollow")
    @ResponseBody
    public String unfollow(Integer entityType, Integer entityId) {
        UserDO user = hostHolder.getUser();

        followService.unfollow(user.getId(), entityType, entityId);

        return CommunityUtil.getJSONString(0, "已取消关注！");
    }

    @GetMapping("/followees/{userId}")
    public String listFollowees(@PathVariable("userId") Integer userId, PageDTO page, Model model) {
        UserDO user = userService.getById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在！");
        }
        model.addAttribute("user", user);

        page.setPageSize(5);
        page.setPath("/followees/" + userId);
        page.setRows((int) followService.getFolloweeCount(userId, ENTITY_TYPE_USER));

        List<Map<String, Object>> userList = followService.listFollowees(userId, page.getOffset(), page.getPageSize());
        if (userList != null) {
            for (Map<String, Object> map : userList) {
                UserDO u = (UserDO) map.get("user");
                map.put("hasFollowed", hasFollowed(u.getId()));
            }
        }
        model.addAttribute("users", userList);
        model.addAttribute("page", page);

        return "/site/followee";
    }

    @GetMapping("/followers/{userId}")
    public String listFollowers(@PathVariable("userId") Integer userId, PageDTO page, Model model) {
        UserDO user = userService.getById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在！");
        }
        model.addAttribute("user", user);

        page.setPageSize(5);
        page.setPath("/followees/" + userId);
        page.setRows((int) followService.getFollowerCount(ENTITY_TYPE_USER, userId));

        List<Map<String, Object>> userList = followService.listFollowers(userId, page.getOffset(), page.getPageSize());
        if (userList != null) {
            for (Map<String, Object> map : userList) {
                UserDO u = (UserDO) map.get("user");
                map.put("hasFollowed", hasFollowed(u.getId()));
            }
        }
        model.addAttribute("users", userList);
        model.addAttribute("page", page);

        return "/site/follower";
    }

    private boolean hasFollowed(Integer userId) {
        if (hostHolder.getUser() == null) {
            return false;
        }
        return followService.hasFollowed(hostHolder.getUser().getId(), ENTITY_TYPE_USER, userId);
    }

}
