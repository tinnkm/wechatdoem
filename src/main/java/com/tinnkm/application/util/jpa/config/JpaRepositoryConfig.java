package com.tinnkm.application.util.jpa.config;

import com.tinnkm.application.util.jpa.factory.BaseRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableJpaRepositories(basePackages = "**.dao",repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
@EnableSpringDataWebSupport
public class JpaRepositoryConfig {
}
