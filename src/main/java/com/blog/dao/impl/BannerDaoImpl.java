package com.blog.dao.impl;

import com.blog.dao.BannerDao;
import com.blog.model.Banner;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDaoImpl implements BannerDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Banner> selectBannerList() {
        try {
            return sqlSessionTemplate.selectList("selectBannerList");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
