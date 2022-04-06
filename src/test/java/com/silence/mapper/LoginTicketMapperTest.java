package com.silence.mapper;

import com.silence.DO.LoginTicketDO;
import com.silence.NowcoderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class LoginTicketMapperTest {
    @Autowired
    LoginTicketMapper loginTicketMapper;

    @Test
    public void testSave() {
        LoginTicketDO loginTicket = new LoginTicketDO();
        loginTicket.setId(1);
        loginTicket.setUserId(111);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.save(loginTicket);
    }

    @Test
    public void testGetByTicket() {
        System.out.println(loginTicketMapper.getByTicket("abc"));
        System.out.println(loginTicketMapper.getByTicket("a"));
    }

    @Test
    public void testUpdateStatusByTicket() {
        loginTicketMapper.updateStatusByTicket("abc", 1);
        System.out.println(loginTicketMapper.getByTicket("abc"));
    }
}
