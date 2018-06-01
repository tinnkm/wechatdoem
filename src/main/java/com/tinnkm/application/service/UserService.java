package com.tinnkm.application.service;

import com.tinnkm.application.model.User;
import com.tinnkm.application.util.result.Result;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname UserService
 * @description 用户相关服务类
 * @date 2018/5/31 13:33
 **/
public interface UserService {
    /**
     * 用户注册
     * @param user 待注册用户
     * @return 注册后的用户
     */
    User register(User user);

    /**
     * 校验用户名是否存在
     * @param userName 用户名
     * @return 存在/不存在
     */
    boolean checkUsername(String userName);

    /**
     * 更新密码
     * @param userName 用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return 更新结果
     */
    Result updatePassword(String userName, String oldPassword, String newPassword);

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 用户密码
     * @return 登陆成功后返回当前用户
     */
    Result login(String username, String password);
}
