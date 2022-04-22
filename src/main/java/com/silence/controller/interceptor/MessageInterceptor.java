package com.silence.controller.interceptor;

import com.silence.DO.UserDO;
import com.silence.service.MessageService;
import com.silence.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MessageInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private MessageService messageService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserDO user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            int unreadLetterCount = messageService.countUnreadLetters(user.getId(), null);
            int unreadNoticeCount = messageService.countUnreadNotice(user.getId(), null);
            modelAndView.addObject("allUnreadCount", unreadNoticeCount + unreadLetterCount);
        }
    }

}
