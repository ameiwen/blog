package com.blog.dao;

import com.blog.model.Tag;

import java.util.List;

public interface TagDao {

    List<Tag> selectTagList();

}