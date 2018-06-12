package com.tinnkm.application.util.jpa.interfaces;

import com.tinnkm.application.util.jpa.ext.DynamicConditionAbstract;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

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

    /**
     * 动态查询
     * @param dynamicCondition 动态查询条件
     * @return 查询的分页结果
     */
    Slice<T> findSelective(DynamicConditionAbstract dynamicCondition);
}
