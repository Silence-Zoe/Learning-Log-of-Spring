package com.silence.mapper;

import com.silence.DO.MessageDO;
import com.silence.NowcoderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class MessageMapperTest {

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void testListConversationsPageByUserId() {
        final List<MessageDO> messages = messageMapper.listConversationsPageByUserId(111, 0, 20);
        messages.forEach(System.out::println);
    }

    @Test
    public void testCountConversationsByUserId() {
        System.out.println(messageMapper.countConversationsByUserId(111));
    }

    @Test
    public void testListLettersByConversationId() {
        final List<MessageDO> letters = messageMapper.listLettersByConversationId("111_112", 0, 10);
        letters.forEach(System.out::println);
    }

    @Test
    public void testCountLettersByConversationId() {
        System.out.println(messageMapper.countLettersByConversationId("111_112"));
    }

    @Test
    public void testCountUnreadLetters() {
        System.out.println(messageMapper.countUnreadLetters(131, "111_131"));
    }
}
