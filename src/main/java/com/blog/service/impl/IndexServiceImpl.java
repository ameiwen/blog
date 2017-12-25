package com.blog.service.impl;

import com.blog.cache.RedisCache;
import com.blog.dao.ArticleDao;
import com.blog.dao.BannerDao;
import com.blog.model.Article;
import com.blog.model.Banner;
import com.blog.service.IndexService;
import com.blog.utils.RelativeDateUtils;
import com.blog.utils.ResponseUtils;
import com.blog.vo.ResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndexServiceImpl implements IndexService{

    private static final Logger logger = LogManager.getLogger(IndexServiceImpl.class);

    @Autowired
    private BannerDao bannerDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public ResponseWrapper IndexInit() {
        String cacheKey = "LatelyArticle";
        try {
            RedisCache redisCache = RedisCache.getInstance();
            boolean flag = redisCache.existsCache(cacheKey);
            List<Banner> bannerList = bannerDao.selectBannerList();//轮播图列表
            List<Article> articleList = null;
            if(flag){
                articleList = (List<Article>) redisCache.getCacheForObject(cacheKey);
            }else{
                articleList= articleDao.selectLatelyArticle();//最近文章
                if(articleList!=null){
                    for(Article article : articleList){
                        article.setTime(RelativeDateUtils.format(article.getCreateTime()));
                    }
                }
                redisCache.addCacheForObject(cacheKey,articleList,5*60);
            }
            ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
            responseWrapper.addAttribute("bannerList",bannerList);
            responseWrapper.addAttribute("articleList",articleList);
        }catch (Exception e){
            logger.error("index init error");
        }
        return ResponseUtils.errorResponse("index init error");
    }
}
