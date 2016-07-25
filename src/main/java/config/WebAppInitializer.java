package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by yehao on 16/7/15.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * 设置spring上下文配置类
     *
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        //激活profile
        System.setProperty("spring.profiles.active", "pro");
        return new Class<?>[]{SpringConfig.class};
    }

    /**
     * 设置springmvc上下文配置类
     *
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringmvcConfig.class};
    }

    /**
     * Dispatcher映射的地址
     *
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
