package com.idg;

import com.idg.config.SpringConfig;
import com.idg.config.SpringmvcConfig;
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
