package com.tinnkm.application.util.jpa.interfaces.impl;

import com.tinnkm.application.util.jpa.ext.DynamicConditionAbstract;
import com.tinnkm.application.util.jpa.interfaces.BaseRepository;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tinnkm
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>, JpaSpecificationExecutor<T> {
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
        if (null != id) {
            Object dbEntity = em.find(entity.getClass(), id);
            Arrays.stream(declaredFields).forEach(field -> {
                if (Modifier.isStatic(field.getModifiers())) {
                    // 此处的return表示执行下一次循环
                    return;
                } else {
                    field.setAccessible(true);
                    try {
                        Object update = field.get(entity);
                        if (null != update) {
                            field.set(dbEntity, update);
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
    public Slice<T> findSelective(DynamicConditionAbstract dynamicCondition,Pageable pageable) {
        //todo : test this mothod
        // 获取所有字段
        Field[] declaredFields = dynamicCondition.getClass().getDeclaredFields();
        Specification<T> specification = Specification.where((root, query, cb) -> null);
        Arrays.stream(declaredFields).forEach(filed -> {
            filed.setAccessible(true);
            try {
                Object value = filed.get(dynamicCondition);
                if (null != value) {
                    specification.and((root, query, cb) -> {
                        String name = filed.getName();
                        TypeEnums typeEnums = TypeEnums.fromProperty(name);
//                        if (null != typeEnums){
                        // 表示字段是带type信息的需要做处理
                        // 获取实际字段值
                        String realFiled = typeEnums.extractProperty(name);
                        switch (typeEnums) {
                            case LIKE:
                                cb.like(root.get(realFiled), value + "%");
                                break;
                            case AFTER:
                                cb.greaterThan(root.get(realFiled).type().as(Date.class), (Date) value);
                                break;
                            case BEFORE:
                                cb.lessThan(root.get(realFiled).type().as(Date.class), (Date) value);
                                break;
                            case NOT_LIKE:
                                cb.notLike(root.get(realFiled), value.toString());
                                break;
                            case SIMPLE_PROPERTY:
                                cb.equal(root.get(realFiled), value);
                                break;
                            default:
                                break;
                        }
                        return null;
                    });
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                logger.error("can't get the filed value");
            }

        });
        return super.findAll(specification,pageable);
    }

    /**
     * @author tinnkm
     * @version 1.0
     * @classname TypeEnums
     * @description TODO
     * @date 2018/6/13 20:37
     **/
    public enum TypeEnums {
        BEFORE(new String[]{"IsBefore", "Before"}),
        AFTER(new String[]{"IsAfter", "After"}),
        NOT_LIKE(new String[]{"IsNotLike", "NotLike"}),
        LIKE(new String[]{"IsLike", "Like"}),
        //        STARTING_WITH(new String[]{"IsStartingWith", "StartingWith", "StartsWith"}),
//        ENDING_WITH(new String[]{"IsEndingWith", "EndingWith", "EndsWith"}),
        SIMPLE_PROPERTY(new String[]{"Is", "Equals"});
        private static final List<TypeEnums> ALL = Arrays.asList(BEFORE, AFTER, NOT_LIKE, LIKE, SIMPLE_PROPERTY);
        public static final Collection<String> ALL_KEYWORDS;
        private final List<String> keywords;
        private final int numberOfArguments;

        private TypeEnums(int numberOfArguments, String... keywords) {
            this.numberOfArguments = numberOfArguments;
            this.keywords = Arrays.asList(keywords);
        }

        private TypeEnums(String... keywords) {
            this(1, keywords);
        }

        static {
            List<String> allKeywords = new ArrayList();
            Iterator var1 = ALL.iterator();

            while (var1.hasNext()) {
                TypeEnums type = (TypeEnums) var1.next();
                allKeywords.addAll(type.keywords);
            }

            ALL_KEYWORDS = Collections.unmodifiableList(allKeywords);
        }

        public String extractProperty(String part) {
            String candidate = StringUtils.uncapitalize(part);
            Iterator var3 = this.keywords.iterator();

            String keyword;
            do {
                if (!var3.hasNext()) {
                    return candidate;
                }

                keyword = (String) var3.next();
            } while (!candidate.endsWith(keyword));

            return candidate.substring(0, candidate.length() - keyword.length());
        }

        public static TypeEnums fromProperty(String rawProperty) {
            Iterator var1 = ALL.iterator();

            TypeEnums type;
            do {
                if (!var1.hasNext()) {
                    return SIMPLE_PROPERTY;
                }

                type = (TypeEnums) var1.next();
            } while (!type.supports(rawProperty));

            return type;
        }

        public Collection<String> getKeywords() {
            return Collections.unmodifiableList(this.keywords);
        }

        protected boolean supports(String property) {
            Iterator var2 = this.keywords.iterator();

            String keyword;
            do {
                if (!var2.hasNext()) {
                    return false;
                }

                keyword = (String) var2.next();
            } while (!property.endsWith(keyword));

            return true;
        }
    }
}
