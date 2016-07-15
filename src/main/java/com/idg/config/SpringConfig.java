package com.idg.config;

import com.idg.common.DemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by yehao on 16/7/15.
 * spring配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.idg"})
@PropertySource("classpath:demo.properties")
public class SpringConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DemoBean demo() {
        return new DemoBean(environment.getProperty("param"));
    }
}
