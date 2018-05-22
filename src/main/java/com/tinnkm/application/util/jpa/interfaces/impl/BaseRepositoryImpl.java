package com.tinnkm.application.util.jpa.interfaces.impl;

import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
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
}
