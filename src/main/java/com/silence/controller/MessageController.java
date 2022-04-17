package com.silence.controller;

import com.silence.DO.MessageDO;
import com.silence.DO.UserDO;
import com.silence.VO.PageVO;
import com.silence.service.MessageService;
import com.silence.service.UserService;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @GetMapping("/letter/list")
    public String getLetterList(Model model, PageVO page) {
        UserDO user = hostHolder.getUser();

        page.setPageSize(5);
        page.setPath("/letter/list");
        page.setRows(messageService.countConversationsByUserId(user.getId()));

        List<MessageDO> conversationList = messageService.listConversationsPageByUserId(user.getId(), page.getOffset(), page.getPageSize());
        System.out.println(conversationList);
        List<Map<String, Object>> conversations = new ArrayList<>();
        if (conversationList != null) {
            for (MessageDO message : conversationList) {
                Map<String, Object> map = new HashMap<>();
                map.put("conversation", message);
                map.put("letterCount", messageService.countLettersByConversationId(message.getConversationId()));
                map.put("unreadCount", messageService.countUnreadLetters(user.getId(), message.getConversationId()));
                Integer targetId = user.getId().equals(message.getFromId()) ? message.getToId() : message.getFromId();
                map.put("target", userService.getById(targetId));

                conversations.add(map);
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("conversations", conversations);
        int unreadLetterCount = messageService.countUnreadLetters(user.getId(), null);
        model.addAttribute("unreadLetterCount", unreadLetterCount);

        return "/site/letter";
    }

}
