package com.devopsbuddy9.config;

import com.oracle.webservices.internal.api.message.PropertySet;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by kb on 7/21/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy9.backend.persistence.repositories")
@EntityScan(basePackages = "com.devopsbuddy9.backend.persistence.domain.backend")
@EnableTransactionManagement
@PropertySource("file:///${user.home}/.devopsbuddy6/application-common.properties")
public class ApplicationConfig {
}
