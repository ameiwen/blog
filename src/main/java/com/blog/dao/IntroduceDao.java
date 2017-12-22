package com.blog.dao;

import com.blog.model.Introduce;

public interface IntroduceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Introduce record);

    int insertSelective(Introduce record);

    Introduce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Introduce record);

    int updateByPrimaryKey(Introduce record);
}