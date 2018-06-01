package com.tinnkm.application.service.impl;

import com.tinnkm.application.constent.SystemConstent;
import com.tinnkm.application.dao.UserDao;
import com.tinnkm.application.model.User;
import com.tinnkm.application.service.UserService;
import com.tinnkm.application.util.encode.EncodeUtils;
import com.tinnkm.application.util.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname UserServiceImpl
 * @description 用户业务实现类
 * @date 2018/5/31 13:35
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User register(User user) {
        user.setId(UUID.randomUUID().toString());
        return userDao.saveAndFlush(user);
    }

    @Override
    public boolean checkUsername(String userName) {
        return userDao.countByName(userName) > 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result updatePassword(String userName, String oldPassword, String newPassword) {
        User user = userDao.findByName(userName);
        if (null == user){
            return Result.failed("传入信息有误，系统中无对应用户");
        }
        String encodeOldPassword = EncodeUtils.md5(oldPassword + SystemConstent.SALT);
        if (user.getPassword().equals(encodeOldPassword)){
            user.setPassword(EncodeUtils.md5(newPassword+SystemConstent.SALT));
            userDao.saveAndFlush(user);
            return Result.success("更新密码成功");
        }else{
            return Result.failed("原密码错误");
        }
    }

    @Override
    public Result login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return Result.failed("传入用户名或密码为空");
        }
        User user = userDao.findByNameAndPassword(username, password);
        if (null == user){
            return Result.failed("用户名或密码错误");
        }
        return Result.success("登陆成功");
    }

}
