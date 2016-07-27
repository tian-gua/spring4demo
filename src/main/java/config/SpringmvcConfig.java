package config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 * Created by yehao on 16/7/15.
 * springmvc配置类
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.idg"}, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class)})
public class SpringmvcConfig extends WebMvcConfigurerAdapter {


    /**
     * 配置velocity
     *
     * @return
     */
    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("/WEB-INFO/view/");
        return velocityConfigurer;
    }

    /**
     * 配置velocity视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        VelocityViewResolver velocityViewResolver = new VelocityViewResolver();
        velocityViewResolver.setSuffix(".vm");
        return velocityViewResolver;
    }

    /**
     * 配置默认视图解析器
     *
     * @return
     */
    @Bean
    public ViewResolver defaultViewResolver() {
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


}
