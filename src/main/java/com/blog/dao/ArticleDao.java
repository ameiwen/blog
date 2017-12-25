package com.blog.dao;

import com.blog.model.Article;

import java.util.List;

public interface ArticleDao {

    List<Article> selectLatelyArticle();

    List<Article> selectRecommendArticle();

    List<Article> selectFileDate();

}