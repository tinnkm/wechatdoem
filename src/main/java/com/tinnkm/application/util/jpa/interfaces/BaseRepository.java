package com.tinnkm.application.util.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author tinnkm
 */
@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    /**
     * 消极更新
     * @param entry 实体
     * @return 影响行数
     */
    int updateSelective(T entry);
}
