package com.silence.mapper;

import com.silence.DO.UserDO;
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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetById() {
        System.out.println(userMapper.getById(11));
    }

    @Test
    public void testGetByName() {
        System.out.println(userMapper.getByName("aaa"));
    }

    @Test
    public void testGetByEmail() {
        System.out.println(userMapper.getByEmail("nowcoder11@sina.com"));
    }

    @Test
    public void testSave() {
        UserDO user = new UserDO(null, "silence", "12345678", "it is a salt", "silence1874@foxmail.com", 3, 1, "666", "http://www.baidu.com", new Date());
        int x = userMapper.save(user);
        System.out.println(x);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateStatusById() {
        System.out.println(userMapper.updateStatusById(152, 888));
    }

    @Test
    public void testUpdateHeaderUrlById() {
        System.out.println(userMapper.updateHeaderUrlById(153, "www.silence.com"));
    }

    @Test
    public void testUpdatePasswordById() {
        System.out.println(userMapper.updateUserPasswordById(151, "987654321"));
    }
}
