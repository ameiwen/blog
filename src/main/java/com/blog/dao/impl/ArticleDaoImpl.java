package com.blog.dao.impl;

import com.blog.dao.ArticleDao;
import com.blog.model.Article;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Article> selectLatelyArticle() {
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("startIndex",0);
            map.put("endIndex",8);
            return sqlSessionTemplate.selectList("selectLatelyArticle",map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> selectRecommendArticle() {
        try {
            Map<String,Object> map = new HashMap<>();
            map.put("startIndex",0);
            map.put("endIndex",8);
            map.put("isRecommend","1");
            return sqlSessionTemplate.selectList("selectRecommendArticle",map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Article> selectFileDate() {
        try {
            return sqlSessionTemplate.selectList("selectFileDate");
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
