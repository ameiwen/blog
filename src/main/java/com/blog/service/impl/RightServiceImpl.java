package com.blog.service.impl;

import com.blog.cache.RedisCache;
import com.blog.dao.ArticleDao;
import com.blog.dao.TagDao;
import com.blog.model.Article;
import com.blog.model.Tag;
import com.blog.service.RightService;
import com.blog.utils.ResponseUtils;
import com.blog.vo.FileDateObject;
import com.blog.vo.ResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RightServiceImpl implements RightService {

    private static final Logger logger = LogManager.getLogger(RightServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private TagDao tagDao;

    @Override
    public ResponseWrapper mainRightInit() {
        String cacheKey = "mainRightInit";
        try {
            Map<String,Object> data = new HashMap<>();
            RedisCache redisCache = RedisCache.getInstance();
            boolean flag = redisCache.existsCache(cacheKey);
            if(flag){
                data = (Map<String, Object>) redisCache.getCacheForObject(cacheKey);
            }else {
                List<FileDateObject> fileDateList = new ArrayList<>();
                List<Tag> tagList = tagDao.selectTagList();
                List<Article> recommendList = articleDao.selectRecommendArticle();
                List<Article> dateList = articleDao.selectFileDate();
                if (dateList != null) {
                    FileDateObject fileDateObject = null;
                    for (Article date : dateList) {
                        fileDateObject = new FileDateObject();
                        fileDateObject.setFileDate(date.getFileDate());
                        fileDateList.add(fileDateObject);
                    }
                }
                data.put("fileDateList",fileDateList);
                data.put("tagList",tagList);
                data.put("recommendList",recommendList);
                ResponseWrapper responseWrapper = ResponseUtils.successResponse("");
                responseWrapper.addAttribute("data",data);
                return responseWrapper;
            }
        }catch (Exception e){
            logger.error("main right init error",e);
        }
        return ResponseUtils.errorResponse("main right init error");
    }
}
