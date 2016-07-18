package com.idg.demo.service;

import com.idg.demo.dao.ModuleMapper;
import com.idg.demo.domain.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yehao on 16/7/18.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * @param id
     */
    public Module findModule(int id) {
        return moduleMapper.selectByPrimaryKey(id);
    }
}
