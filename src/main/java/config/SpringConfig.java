package config;

import com.idg.common.cache.DemoCache;
import com.idg.common.mybatis.MybatisMapper;
import net.sf.ehcache.CacheManager;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by yehao on 16/7/15.
 * spring配置类
 */
@Configuration
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan(basePackages = {"com.idg"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
@PropertySource("classpath:demo.properties")
@EnableTransactionManagement(proxyTargetClass = true)
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

    /**
     * 缓存管理器
     *
     * @param cacheManager
     * @return
     */
    @Bean
    public EhCacheCacheManager cacheManager(CacheManager cacheManager) {
        return new EhCacheCacheManager(cacheManager);
    }


    /**
     * 缓存管理器工厂
     *
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehcache() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        return ehCacheManagerFactoryBean;
    }

    /**
     * 缓存操作对象
     *
     * @param ehCacheCacheManager
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DemoCache demoCache(EhCacheCacheManager ehCacheCacheManager) {
        DemoCache demoCache = new DemoCache();
        //获得xml中的cache
        demoCache.setCache(ehCacheCacheManager.getCache("demoCache"));
        return demoCache;
    }

    /**
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
