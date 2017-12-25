package com.blog.dao;

import com.blog.model.Banner;

import java.util.List;

public interface BannerDao {

    List<Banner> selectBannerList();

}