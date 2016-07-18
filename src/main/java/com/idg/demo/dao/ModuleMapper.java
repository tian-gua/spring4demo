package com.idg.demo.dao;

import com.idg.common.mybatis.MybatisMapper;
import com.idg.demo.domain.Module;

public interface ModuleMapper extends MybatisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}