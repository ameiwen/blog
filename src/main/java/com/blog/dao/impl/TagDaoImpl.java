package com.blog.dao.impl;

import com.blog.dao.TagDao;
import com.blog.model.Tag;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Tag> selectTagList() {
        try {
            return sqlSessionTemplate.selectList("selectTagList");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
