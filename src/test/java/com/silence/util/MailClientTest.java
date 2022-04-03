package com.silence.util;

import com.silence.NowcoderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class MailClientTest {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("silence1874@foxmail.com", "TEST", "Hello~~");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "silence");
        String content = templateEngine.process("/mail/demo", context);

        mailClient.sendMail("silence1874@foxmail.com", "HTML", content);
    }

}
