package com.blog.dao;

import com.blog.model.CodeTask;

public interface CodeTaskDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CodeTask record);

    int insertSelective(CodeTask record);

    CodeTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CodeTask record);

    int updateByPrimaryKey(CodeTask record);
}