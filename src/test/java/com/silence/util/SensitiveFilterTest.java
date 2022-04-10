package com.silence.util;

import com.silence.NowcoderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class SensitiveFilterTest {
    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void TestSensitiveFilter() {
        String text1 = "hsajdhka";
        String text2 = "呵呵呵呵呵牛阿";
        String text3 = "你    妈的";
        String text4 = "陈卫真是个傻,,,,,逼";
        System.out.println(sensitiveFilter.filter(text1));
        System.out.println(sensitiveFilter.filter(text2));
        System.out.println(sensitiveFilter.filter(text3));
        System.out.println(sensitiveFilter.filter(text4));
    }

}
