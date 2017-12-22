package com.blog.dao;

import com.blog.model.SafeCode;

public interface SafeCodeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SafeCode record);

    int insertSelective(SafeCode record);

    SafeCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SafeCode record);

    int updateByPrimaryKey(SafeCode record);
}