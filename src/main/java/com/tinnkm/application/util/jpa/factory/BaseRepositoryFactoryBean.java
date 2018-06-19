package com.tinnkm.application.util.jpa.factory;

import com.tinnkm.application.util.jpa.interfaces.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author tinnkm
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T,I>,T,I extends Serializable> extends JpaRepositoryFactoryBean<R,T,I> {

    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory extends JpaRepositoryFactory{
        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }


        @Override
        protected  SimpleJpaRepository<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new BaseRepositoryImpl<>((Class<?>) information.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }
}
