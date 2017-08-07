package com.devopsbuddy9.config;

import com.devopsbuddy9.backend.service.EmailService;
import com.devopsbuddy9.backend.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kb on 7/16/2017.
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/.devopsbuddy6/application-prod.properties")
public class ProductionConfig {


    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
