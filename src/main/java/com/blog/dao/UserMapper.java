package com.blog.dao;

import com.blog.model.User;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

}