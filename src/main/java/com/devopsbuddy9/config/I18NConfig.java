package com.devopsbuddy9.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Created by kb on 7/14/2017.
 */
@Configuration
public class I18NConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:i18n/messages");
        reloadableResourceBundleMessageSource.setCacheSeconds(1800);
        return reloadableResourceBundleMessageSource;
    }

}
