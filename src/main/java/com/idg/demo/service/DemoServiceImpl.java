package com.idg.demo.service;

import com.idg.common.cache.DemoCache;
import com.idg.demo.dao.ModuleMapper;
import com.idg.demo.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by yehao on 16/7/18.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private DemoCache demoCache;

    /**
     * @param id
     */
    public Module findModule(int id) {
        Object o = demoCache.get(id);
        if (o == null) {
            System.out.println("未找到缓存");
            demoCache.put(id, moduleMapper.selectByPrimaryKey(id));
            return (Module) demoCache.get(id);
        } else {
            System.out.println("找到缓存");
            return (Module) o;
        }

    }
}
