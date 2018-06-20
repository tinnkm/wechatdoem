package com.tinnkm.application.util.jpa.interfaces;

import com.tinnkm.application.util.jpa.ext.DynamicConditionInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author tinnkm
 */
@NoRepositoryBean
public interface BaseRepository<T,R extends Serializable> extends JpaRepository<T,R> {
    /**
     * 消极更新
     * @param entry 实体
     * @return 影响行数
     */
    int updateSelective(T entry);

    /**
     * 动态查询
     * @param dynamicCondition 动态查询条件
     * @return 查询的分页结果
     */
    Page<T> findSelective(DynamicConditionInterface dynamicCondition, Pageable pageable);
}
