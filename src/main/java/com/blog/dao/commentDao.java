package com.blog.dao;

import com.blog.model.comment;

public interface commentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(comment record);

    int insertSelective(comment record);

    comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(comment record);

    int updateByPrimaryKey(comment record);
}