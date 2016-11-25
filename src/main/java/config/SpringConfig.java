package config;

import com.idg.common.cache.DemoCache;
import com.idg.common.mybatis.MybatisMapper;
import com.rabbitmq.client.Channel;
import net.sf.ehcache.CacheManager;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
     * @param ehCacheCacheManager ehcache缓存管理器
     * @return DemoCache bean
     * @author yehao
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
     * 事务管理器
     *
     * @param dataSource 数据源
     * @return DataSourceTransactionManager bean
     * @author yehao
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    /**
     * 创建rabbitmq连接工厂
     *
     * @return ConnectionFactory bean
     * @author yehao
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("127.0.0.1");
        cachingConnectionFactory.setUsername("mq_yh");
        cachingConnectionFactory.setPassword("mq_yh");
//        cachingConnectionFactory.setPort(AMQP.PROTOCOL.PORT);
        cachingConnectionFactory.setVirtualHost("local_mq");
        return cachingConnectionFactory;
    }

    /**
     * 创建rabbitmq模板bean
     *
     * @param connectionFactory rabbitmq连接工厂
     * @return RabbitTemplate bean
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setRoutingKey("local_queue");
        rabbitTemplate.setQueue("local_queue");
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames("local_queue");
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
            /**
             * Callback for processing a received Rabbit message.
             * <p>Implementors are supposed to process the given Message,
             * typically sending reply messages through the given Session.
             *
             * @param message the received AMQP message (never <code>null</code>)
             * @param channel the underlying Rabbit Channel (never <code>null</code>)
             * @throws Exception Any.
             */
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println(message);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });
        return simpleMessageListenerContainer;
    }

}

