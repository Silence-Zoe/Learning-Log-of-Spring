package com.silence.mapper;

import com.silence.DO.DiscussPostDO;
import com.silence.NowcoderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class DiscussPostMapperTest {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testListPage() {
        var list = discussPostMapper.listPage(103, 2,4);
        for (var dp : list) {
            System.out.println(dp);
        }
    }

    @Test
    public void testCountRows() {
        int row = discussPostMapper.countRows(103);
        System.out.println(row);
        System.out.println(discussPostMapper.countRows(0));
    }

    @Test
    public void testSaveDiscussPost() {
        DiscussPostDO discussPost = new DiscussPostDO(null, 150, "test", "body", 0, 0, new Date(), 0, 0d);
        discussPostMapper.saveDiscussPost(discussPost);
    }

}
