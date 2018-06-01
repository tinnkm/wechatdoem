package com.tinnkm.application.dao;

import com.tinnkm.application.model.User;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author tinnkm
 */
public interface UserDao extends BaseRepository<User,String> {
    /**
     * 更新并刷新缓存
     * @param user 待更新实体
     * @return 更新完之后实体
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Override
    User saveAndFlush(User user);


    /**
     * 获取改用户名记录数
     * @param name 用户名
     * @return 记录数
     */
    int countByName(String name);

    /**
     * 根据用户名查找用户
     * @param name 用户名
     * @return 用户
     */
    User findByName(String name);

    /**
     * 根据用户名和密码查找用户
     * @param name 用户名
     * @param password 密码
     * @return 用户实体
     */
    User findByNameAndPassword(String name,String password);
}
