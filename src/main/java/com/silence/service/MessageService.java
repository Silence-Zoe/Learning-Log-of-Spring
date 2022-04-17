package com.silence.service;

import com.silence.DO.MessageDO;
import com.silence.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

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

}
