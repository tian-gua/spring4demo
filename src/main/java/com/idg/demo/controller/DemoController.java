package com.idg.demo.controller;

import com.idg.demo.domain.Module;
import com.idg.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yehao on 16/7/15.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;


    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("demo/demo");
    }

    @RequestMapping("/hello")
    public ModelAndView hello() {
        return new ModelAndView("index");
    }

    @RequestMapping("/json")
    public Map<String, Object> json() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("hello", "spring4");
        return model;
    }

    @RequestMapping("/test")
    public Module findModule(Integer id) {
        return demoService.findModule(id);
    }
}
