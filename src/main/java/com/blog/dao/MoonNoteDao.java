package com.blog.dao;

import com.blog.model.MoonNote;

public interface MoonNoteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MoonNote record);

    int insertSelective(MoonNote record);

    MoonNote selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MoonNote record);

    int updateByPrimaryKey(MoonNote record);
}