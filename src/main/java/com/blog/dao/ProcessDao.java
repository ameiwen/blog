package com.blog.dao;

import com.blog.model.Process;

public interface ProcessDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Process record);

    int insertSelective(Process record);

    Process selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);
}