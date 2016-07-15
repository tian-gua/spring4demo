package com.idg.config;

import com.idg.common.DemoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
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
    @Profile("pro")
    public DemoBean demo() {
        return new DemoBean(environment.getProperty("param"));
    }

    @Bean()
    @Profile("dev")
    public DemoBean demo2() {
        return new DemoBean("abc");
    }
}
