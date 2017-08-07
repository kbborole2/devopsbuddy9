package com.devopsbuddy9.config;

import com.devopsbuddy9.backend.service.EmailService;
import com.devopsbuddy9.backend.service.MockEmailService;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.ServletRegistration;

/**
 * Created by tedonema on 21/03/2016.
 */
@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/.devopsbuddy6/application-dev.properties")
public class DevelopmentConfig {


    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());

        bean.addUrlMappings("/console/*");
        return bean;
    }




}
