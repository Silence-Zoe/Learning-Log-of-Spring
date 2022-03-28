package com.silence.mapper;

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

}
