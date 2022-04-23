package com.silence.controller;

import com.silence.DO.DiscussPostDO;
import com.silence.DTO.PageDTO;
import com.silence.service.ElasticsearchService;
import com.silence.service.LikeService;
import com.silence.service.UserService;
import com.silence.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController implements CommunityConstant {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @GetMapping("/search")
    public String search(String keyword, PageDTO page, Model model) {
        Page<DiscussPostDO> searchResult = elasticsearchService.searchDiscussPost(keyword, page.getPageNum() - 1, page.getPageSize());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (searchResult != null) {
            for (DiscussPostDO post : searchResult) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                map.put("user", userService.getById(post.getUserId()));
                map.put("likeCount", likeService.getEntityLikeCount(ENTITY_TYPE_POST, post.getId()));
                discussPosts.add(map);
            }
        }
        page.setPath("/search?keyword=" + keyword);
        page.setRows(searchResult == null ? 0 : (int) searchResult.getTotalElements());

        model.addAttribute("page", page);
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("keyword", keyword);

        return "/site/search";
    }

}
