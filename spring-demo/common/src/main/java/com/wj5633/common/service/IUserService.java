package com.wj5633.common.service;


import com.wj5633.common.model.User;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/3 13:25
 * @description
 */

public interface IUserService {
    User findByName(String userName);
}
