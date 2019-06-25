package com.zentity.demo.jsonplaceholder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;

@Configuration
public class ApplicationConfig {

    @Bean
    public Filter eTagFilter() {
        return new ShallowEtagHeaderFilter();
    }


}
