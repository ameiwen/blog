package com.blog.service;

import com.blade.ioc.annotation.Bean;
import com.blog.model.entity.Visited;
import com.blog.utils.BlogUtils;

import java.util.Map;

@Bean
public class VisitedService {

    /**
     * 保存浏览网站ip信息
     * @param ip
     */
    public void saveVisited(String ip){
        Visited visited = new Visited().where("ip",ip).find();
        if(null!=visited){
            visited.setVisited_num(visited.getVisited_num()+1);
            visited.setCreate_time((int) (System.currentTimeMillis()/1000));
            visited.update(visited.getId());
        }else{
            Map<String,Object> address = BlogUtils.getIpAddress(ip);
            if(address!=null){
                visited = new Visited();
                visited.setIp((String) address.get("ip"));
                visited.setCountry((String) address.get("country"));
                visited.setArea((String) address.get("area"));
                visited.setRegion((String) address.get("region"));
                visited.setCity((String) address.get("city"));
                visited.setCounty((String) address.get("county"));
                visited.setCreate_time((int) (System.currentTimeMillis()/1000));
                visited.save();
            }
        }
    }

    /**
     * 删除访问记录
     * @param id
     */
    public void removeVisited(Integer id){
        if(null !=id){
            new Visited().delete(id);
        }
    }

}
