package com.silence.controller;

import com.silence.DO.DiscussPostDO;
import com.silence.DO.UserDO;
import com.silence.service.DiscussPostService;
import com.silence.service.UserService;
import com.silence.util.CommunityUtil;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        UserDO user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "请先登录！");
        }

        DiscussPostDO discussPost = new DiscussPostDO();
        discussPost.setUserId(user.getId());
        discussPost.setTitle(title);
        discussPost.setContent(content);
        discussPost.setType(0);
        discussPost.setStatus(0);
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);

        return CommunityUtil.getJSONString(0, "发布成功！");
    }


    @GetMapping("/detail/{discussPostId}")
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model) {
        DiscussPostDO post = discussPostService.getById(discussPostId);
        model.addAttribute("post", post);
        UserDO user = userService.getById(post.getUserId());
        model.addAttribute("user", user);
        return "/site/discuss-detail";
    }
}
