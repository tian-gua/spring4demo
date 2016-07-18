package com.idg.config;

import com.idg.common.mybatis.MybatisMapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * Created by yehao on 16/7/15.
 * spring配置类
 */
@Configuration
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan(basePackages = {"com.idg"})
@PropertySource("classpath:demo.properties")
public class SpringConfig {

    @Autowired
    private Environment environment;


    /**
     * mybatis扫描mapper接口
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.idg");
        mapperScannerConfigurer.setMarkerInterface(MybatisMapper.class);
        return mapperScannerConfigurer;
    }


}
