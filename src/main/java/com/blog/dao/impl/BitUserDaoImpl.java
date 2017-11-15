package com.blog.dao.impl;

import com.blog.dao.BitUserDao;
import com.blog.model.BitUser;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BitUserDaoImpl implements BitUserDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public BitUser getUserById(Long userid) {
        try {
            return sqlSessionTemplate.selectOne("selectBitUserById",userid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
