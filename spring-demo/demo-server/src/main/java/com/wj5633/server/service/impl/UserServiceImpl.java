package com.wj5633.server.service.impl;

import com.wj5633.common.model.User;
import com.wj5633.common.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/3 13:26
 * @description
 */

@Service
public class UserServiceImpl implements IUserService {

    private static final Map<String, User> userMap = new HashMap<>();

    static {
        userMap.put("wang", new User("wang", 18));
        userMap.put("test", new User("test", 18));
    }

    @Override
    public User findByName(String userName) {
        return userMap.get(userName);
    }
}
