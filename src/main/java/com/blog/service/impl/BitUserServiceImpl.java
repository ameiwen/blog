package com.blog.service.impl;

import com.blog.dao.UserMapper;
import com.blog.model.User;
import com.blog.service.BitUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitUserServiceImpl implements BitUserService{

    private static final Logger logger = LogManager.getLogger(BitUserServiceImpl.class);

    @Autowired
    private UserMapper userDao;

    @Override
    public User getUser(Integer userid) {
        try {
            return userDao.selectByPrimaryKey(userid);
        }catch (Exception e){
            logger.error("query error"+e);
        }
        return null;
    }
}
