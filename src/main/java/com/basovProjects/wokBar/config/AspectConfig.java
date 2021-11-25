package com.basovProjects.wokBar.config;

import com.basovProjects.wokBar.aop.WebServiceLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.basovProjects.wokBar.aop")
public class AspectConfig {
    @Bean
    public WebServiceLogger webServiceLogger() {
        return new WebServiceLogger();
    }
}
