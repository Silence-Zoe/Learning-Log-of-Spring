package com.silence.service;

import com.silence.DO.MessageDO;
import com.silence.mapper.MessageMapper;
import com.silence.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<MessageDO> listConversationsPageByUserId(Integer userId, int offset, int pageSize) {
        return messageMapper.listConversationsPageByUserId(userId, offset, pageSize);
    }

    public int countConversationsByUserId(Integer userId) {
        return messageMapper.countConversationsByUserId(userId);
    }

    public List<MessageDO> listLettersByConversationId(String conversationId, int offset, int pageSize) {
        return messageMapper.listLettersByConversationId(conversationId, offset, pageSize);
    }

    public int countLettersByConversationId(String conversationId) {
        return messageMapper.countLettersByConversationId(conversationId);
    }

    public int countUnreadLetters(Integer userId, String conversationId) {
        return messageMapper.countUnreadLetters(userId, conversationId);
    }

    public int addMessage(MessageDO message) {
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageMapper.saveMessage(message);
    }

    public int readMessage(List<Integer> ids) {
        return messageMapper.updateStatus(ids, 1);
    }

}
