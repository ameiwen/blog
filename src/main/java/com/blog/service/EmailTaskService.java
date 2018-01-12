package com.blog.service;

import com.blade.ioc.annotation.Bean;
import com.blog.model.entity.Email;

@Bean
public class EmailTaskService {

    public void saveEmailTask(Email email){
        if(null!=email){
            email.save();
        }
    }

}
