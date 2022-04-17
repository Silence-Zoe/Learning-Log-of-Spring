package com.silence.controller;

import com.silence.DO.MessageDO;
import com.silence.DO.UserDO;
import com.silence.VO.PageVO;
import com.silence.service.MessageService;
import com.silence.service.UserService;
import com.silence.util.CommunityUtil;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @GetMapping("/letter/detail/{conversationId}")
    public String getLetterDetail(@PathVariable("conversationId") String conversationId, PageVO page, Model model) {
        page.setPageSize(5);
        page.setPath("/letter/detail/" + conversationId);
        page.setRows(messageService.countLettersByConversationId(conversationId));

        List<MessageDO> letterList = messageService.listLettersByConversationId(conversationId, page.getOffset(), page.getPageSize());
        List<Map<String, Object>> letters = new ArrayList<>();
        if (letterList != null) {
            for (MessageDO message : letterList) {
                Map<String, Object> map = new HashMap<>();
                map.put("letter", message);
                map.put("fromUser", userService.getById(message.getFromId()));
                letters.add(map);
            }
        }
        model.addAttribute("page", page);
        model.addAttribute("letters", letters);
        model.addAttribute("target", getLetterTarget(conversationId));

        List<Integer> ids = getLetterIds(letterList);
        if (!ids.isEmpty()) {
            messageService.readMessage(ids);
        }

        return "/site/letter-detail";
    }

    private UserDO getLetterTarget(String conversationId) {
        String[] ids = conversationId.split("_");
        int id0 = Integer.parseInt(ids[0]);
        int id1 = Integer.parseInt(ids[1]);
        return hostHolder.getUser().getId() == id0 ? userService.getById(id1) : userService.getById(id0);
    }

    private List<Integer> getLetterIds(List<MessageDO> letterList) {
        List<Integer> ids = new ArrayList<>();

        if (letterList != null) {
            for (MessageDO message : letterList) {
                if (hostHolder.getUser().getId() == message.getToId() && message.getStatus() == 0) {
                    ids.add(message.getId());
                }
            }
        }

        return ids;
    }

    @PostMapping("/letter/send")
    @ResponseBody
    public String sendLetter(String toName, String content) {
        UserDO target = userService.getByName(toName);
        if (target == null) {
            return CommunityUtil.getJSONString(1, "目标用户不存在！");
        }

        MessageDO message = new MessageDO();
        message.setFromId(hostHolder.getUser().getId());
        message.setToId(target.getId());
        if (message.getFromId() < message.getToId()) {
            message.setConversationId(message.getFromId() + "_" + message.getToId());
        } else {
            message.setConversationId(message.getToId() + "_" + message.getFromId());
        }
        message.setContent(content);
        message.setCreateTime(new Date());
        message.setStatus(0);
        messageService.addMessage(message);

        return CommunityUtil.getJSONString(0);
    }

}
