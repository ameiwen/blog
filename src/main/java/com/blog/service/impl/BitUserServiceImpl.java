package com.blog.service.impl;

import com.blog.dao.BitUserDao;
import com.blog.model.BitUser;
import com.blog.service.BitUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitUserServiceImpl implements BitUserService{

    private static final Logger logger = LogManager.getLogger(BitUserServiceImpl.class);

    @Autowired
    private BitUserDao userDao;

    @Override
    public BitUser getUser(Long userid) {
        try {
            return userDao.getUserById(userid);
        }catch (Exception e){
            logger.error("query error"+e);
        }
        return null;
    }
}
