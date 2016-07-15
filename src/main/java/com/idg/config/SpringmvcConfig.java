package com.idg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by yehao on 16/7/15.
 * springmvc配置类
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.idg"})
public class SpringmvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INFO/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    /**
     * 配置静态资源处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//
//    public ContentNegotiationManager contentNegotiationManager(){
//        ContentNegotiationManager contentNegotiationManager = new ContentNegotiationManager();
//        contentNegotiationManager.
//        return contentNegotiationManager;
//    }

//    @Bean
//    public View contentNegotiatingViewResolver() {
//        return new MappingJackson2JsonView();
//    }


}
