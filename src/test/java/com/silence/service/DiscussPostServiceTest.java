package com.silence.service;

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
public class DiscussPostServiceTest {
    @Autowired
    DiscussPostService discussPostService;

    @Test
    public void testListPage() {
        System.out.println(discussPostService.listPage(111, 2, 3, 0));
    }

    @Test
    public void testCountRows() {
        System.out.println(discussPostService.countRows(111));
    }
}
