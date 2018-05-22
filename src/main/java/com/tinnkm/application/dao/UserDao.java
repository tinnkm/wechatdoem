package com.tinnkm.application.dao;

import com.tinnkm.application.model.User;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface UserDao extends BaseRepository<User,UUID> {
    User saveAndFlush(User user);
    User findById(String id);
}
