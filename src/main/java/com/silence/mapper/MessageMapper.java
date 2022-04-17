package com.silence.mapper;

import com.silence.DO.MessageDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

    List<MessageDO> listConversationsPageByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    int countConversationsByUserId(@Param("userId") Integer userId);

    List<MessageDO> listLettersByConversationId(@Param("conversationId") String conversationId, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    int countLettersByConversationId(@Param("conversationId") String conversationId);

    int countUnreadLetters(@Param("userId") Integer userId, @Param("conversationId") String conversationId);

    int saveMessage(MessageDO message);

    int updateStatus(@Param("ids") List<Integer> ids, @Param("status") Integer status);

}
