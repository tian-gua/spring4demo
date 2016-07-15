package com.idg.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by yehao on 16/7/15.
 */
@Configuration
@ComponentScan(basePackages = {"com.idg"})
@PropertySource("classpath:demo.properties")
public class SpringConfig {
}
