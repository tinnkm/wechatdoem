package com.tinnkm.application.dao;

import com.tinnkm.application.model.User;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


/**
 * @author tinnkm
 */
public interface UserDao extends BaseRepository<User,UUID> {
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
     * 根据id查找用户
     * @param id 用户id
     * @return 实体
     */
    User findById(String id);
}
