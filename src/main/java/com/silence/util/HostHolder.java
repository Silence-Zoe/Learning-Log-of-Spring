package com.silence.util;

import com.silence.DO.UserDO;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    private ThreadLocal<UserDO> users = new ThreadLocal<>();

    public void setUser(UserDO user) {
        users.set(user);
    }

    public UserDO getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
