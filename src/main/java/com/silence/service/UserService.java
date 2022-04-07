package com.silence.service;

import com.silence.DO.LoginTicketDO;
import com.silence.DO.UserDO;
import com.silence.mapper.LoginTicketMapper;
import com.silence.mapper.UserMapper;
import com.silence.util.CommunityConstant;
import com.silence.util.CommunityUtil;
import com.silence.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService implements CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public UserDO getById(Integer id) {
        return userMapper.getById(id);
    }

    public Map<String, Object> register(UserDO user) {
        Map<String, Object> map = new HashMap<>();

        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "帐号不能为空！");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空！");
            return map;
        }

        if (userMapper.getByName(user.getUsername()) != null) {
            map.put("usernameMsg", "该帐号已存在！");
            return map;
        }
        if (userMapper.getByEmail(user.getEmail()) != null) {
            map.put("emailMsg", "该邮箱已被注册！");
            return map;
        }

        user.setSalt(CommunityUtil.genrateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.genrateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());

        userMapper.save(user);

        Context context = new Context();
        context.setVariable("email", user.getEmail());
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    public int activation(Integer userId, String code) {
        UserDO user = userMapper.getById(userId);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatusById(userId, ACTIVATION_REPEAT);
            return ACTIVATION_SUCCESS;
        } else {
            return ACTIVATION_FAILURE;
        }
    }

    public Map<String, Object> login(String username, String password, long expiredSeconds) {
        Map<String, Object> map = new HashMap<>();

        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "帐号不能为空！");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }

        UserDO user = userMapper.getByName(username);
        if (user == null) {
            map.put("usernameMsg", "该帐号不存在！");
            return map;
        }
        if (user.getStatus() == 0) {
            map.put("usernameMsg", "该帐号未激活！");
            return map;
        }

        password = CommunityUtil.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)) {
            map.put("passwordMsg", "用户名或密码不正确！");
            return map;
        }

        LoginTicketDO loginTicket = new LoginTicketDO();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommunityUtil.genrateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
        loginTicketMapper.save(loginTicket);

        map.put("ticket", loginTicket.getTicket());
        return map;
    }

    public void logout(String ticket) {
        loginTicketMapper.updateStatusByTicket(ticket, 1);
    }

    public LoginTicketDO getLoginTicket(String ticket) {
        return loginTicketMapper.getByTicket(ticket);
    }

    public int updateHeader(Integer userId, String headerUrl) {
        return userMapper.updateHeaderUrlById(userId, headerUrl);
    }
}