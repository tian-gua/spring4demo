package com.idg;

import com.idg.common.filter.DemoFilter;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by yehao on 16/7/21.
 */
public class ServletInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic filter = servletContext.addFilter("DemoFilter", DemoFilter.class);
        filter.addMappingForUrlPatterns(null,false,"/*");
    }
}
