package com.tinnkm.application.controller;

import com.tinnkm.application.model.User;
import com.tinnkm.application.service.UserService;
import com.tinnkm.application.util.result.Result;
import com.tinnkm.application.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname UserController
 * @description 用户香港的controller
 * @date 2018/5/31 13:29
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user 用户信息
     * @return json串
     */
    @PutMapping("/register")
    public Result<User> register(User user) {
        return Result.success(userService.register(user));
    }

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 用户密码
     * @param httpSession session
     * @return json串
     */
    @PostMapping("/login")
    public Result<String> login(String username, String password, HttpSession httpSession) {
        Result result = userService.login(username, password);
        if (result.getCode().equals(ResultCode.SUCCESS)) {
            httpSession.setAttribute("username", username);

        }
        return result;
    }

    /**
     * 修改密码
     * @param username 用户名
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @return json串
     */
    @PostMapping("/changePassword")
    public Result changePassword(@SessionAttribute("username") String username, String oldPassword, String newPassword){
        return userService.updatePassword(username, oldPassword, newPassword);
    }

    /**
     * 用户登出
     * @param username 用户名
     * @param session session
     * @return json串
     */
    @GetMapping("/logout")
    public Result logout(@SessionAttribute String username,HttpSession session){
        try {
            session.removeAttribute(username);
            return Result.success("登出成功");
        } catch (Exception e) {
            return Result.failed(e);
        }
    }
}
