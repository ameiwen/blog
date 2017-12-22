package com.blog.dao.impl;

import com.blog.dao.UserMapper;
import com.blog.model.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserMapper {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public User selectByPrimaryKey(Integer userid) {
        try {
            return sqlSessionTemplate.selectOne("selectByPrimaryKey",userid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
