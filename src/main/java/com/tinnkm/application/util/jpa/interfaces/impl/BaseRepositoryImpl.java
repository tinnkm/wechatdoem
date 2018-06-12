package com.tinnkm.application.util.jpa.interfaces.impl;

import com.tinnkm.application.util.jpa.ext.DynamicConditionAbstract;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author tinnkm
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>,JpaSpecificationExecutor<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    public int updateSelective(T entity) {
        final Serializable id;
        // 获取所有字段
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        id = (Serializable) Arrays.stream(declaredFields).filter(field -> !Modifier.isStatic(field.getModifiers()) && null != field.getAnnotation(Id.class)).map(field -> {
            try {
                return field.get(entity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()).get(0);
        if (null != id){
            Object dbEntity = em.find(entity.getClass(), id);
            Arrays.stream(declaredFields).forEach(field -> {
                if (Modifier.isStatic(field.getModifiers())){
                    // 此处的return表示执行下一次循环
                    return;
                }else{
                    field.setAccessible(true);
                    try {
                        Object update = field.get(entity);
                        if (null != update){
                            field.set(dbEntity,update);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
            em.merge(dbEntity);
        }

        return 0;
    }

    @Override
    public Slice<T> findSelective(DynamicConditionAbstract dynamicCondition) {
        // 获取所有字段
        Field[] declaredFields = dynamicCondition.getClass().getDeclaredFields();
        Specification<T> specification = Specification.where((root, query, cb) -> null);
        Arrays.stream(declaredFields).forEach(filed -> {
            filed.setAccessible(true);
            try {
                Object value = filed.get(dynamicCondition);
                if (null != value ){
                    specification.and((root, query, cb) -> {
//                        if ()
                        // todo:read the jpa source to design this!
                        return null;
                    });
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.error("can't get the filed value");
            }

        });
        return null;
    }
}
